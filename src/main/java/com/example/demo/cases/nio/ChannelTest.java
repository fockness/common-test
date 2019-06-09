package com.example.demo.cases.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelTest {
	
	//利用通道完成文件的复制(非直接缓冲区)
	@Test
	public void testChannel() throws Exception{
		
		//①创建通道
		FileInputStream fis = new FileInputStream("1.png");
		FileOutputStream fos = new FileOutputStream("2.png");

		FileChannel inChannel = fis.getChannel();
		FileChannel outChannel = fos.getChannel();
		
		//②分配指定的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//③将通道中的数据存入缓冲区
		while(inChannel.read(buf) != -1){
			buf.flip();//切换到读数据模式
			//④将缓冲区中的数据写入通道
			outChannel.write(buf);
			buf.clear();//清空缓冲区
		}
		inChannel.close();
		outChannel.close();
		fis.close();
		fos.close();
	}
	
	//利用通道完成文件的复制(直接缓冲区),内存映射文件
	@Test
	public void testChannel2() throws Exception{
		FileChannel inChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
		//StandardOpenOption.CREATE = 文件不存在则创建,存在则覆盖
		//StandardOpenOption.CREATE_NEW = 文件不存在则创建,存在则报错
		FileChannel outChannel = FileChannel.open(Paths.get("3.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
		
		//内存映射文件
		MappedByteBuffer inByteBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outByteBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		
		byte[] b = new byte[inByteBuffer.limit()];
		inByteBuffer.get(b);
		outByteBuffer.put(b);
	}

	@Test
	public void testChannel3() throws Exception{
		//直接缓冲区
		FileChannel incChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("4.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
		
//		outChannel.transferFrom(incChannel, 0, incChannel.size());
		incChannel.transferTo(0, incChannel.size(), outChannel);
		
		outChannel.close();
		incChannel.close();
	}

	@Test
	public void testChannel4() throws Exception{
		//获取通道
		RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
		FileChannel channel1 = raf1.getChannel();
		
		//分配缓冲区
		ByteBuffer buf1 = ByteBuffer.allocate(200);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);
		
		ByteBuffer[] array = {buf1, buf2};
		
		//分散读取
		channel1.read(array);
		for (ByteBuffer byteBuffer : array) {
			byteBuffer.flip();
		}
		
		
		System.out.println(new String(array[0].array(), 0, array[0].limit(), "GBK"));
		System.out.println("-----------");
		System.out.println(new String(array[1].array(), 0, array[1].limit(), "GBK"));
		
		
		//聚集写入
		RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
		FileChannel channel2 = raf2.getChannel();
		channel2.write(buf2);
		
		channel1.close();
		channel2.close();
	}
	
	@Test
	public void testChannel6() throws Exception{
		//支持的字符集种类
//		Map<String, Charset> map = Charset.availableCharsets();
//		Set<Entry<String, Charset>> set = map.entrySet();
//		for (Entry<String, Charset> entry : set) {
//			System.out.println(entry.getKey() + "---" + entry.getValue());
//		}
		
		
		Charset cs = Charset.forName("GBK");
		//获取解码器
		CharsetDecoder cd = cs.newDecoder();
		
		//获取编码器
		CharsetEncoder ce = cs.newEncoder();
		
		CharBuffer cBuf = CharBuffer.allocate(1024);
		cBuf.put("轻量级框架");
		cBuf.flip();
		//编码
		ByteBuffer bBuf = ce.encode(cBuf);
		
		for(int i=0; i<10; i++){
			System.out.println(bBuf.get());
		}
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println(cBuf2.get());
		
		//GBK编码,UTF-8解码,乱码
		bBuf.flip();
		Charset cs2 = Charset.forName("UTF-8");
		CharBuffer cb2 = cs2.decode(bBuf);
		System.out.println(cb2.get());
	}
	
	//NIO的网络通信
	@Test
	public void client() throws Exception{
		//获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		FileChannel fChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
		
		//获取缓冲区
		ByteBuffer bBuf = ByteBuffer.allocate(1024);
		
		while(fChannel.read(bBuf) != -1){
			bBuf.flip();
			sChannel.write(bBuf);
			bBuf.clear();
		}
		sChannel.close();
		fChannel.close();
	}

	@Test
	public void server() throws Exception{
		//获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		
		FileChannel fChannel = FileChannel.open(Paths.get("5.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		//绑定端口
		ssChannel.bind(new InetSocketAddress(9898));
		
		//获取客户端连接的通道
		SocketChannel sChannel = ssChannel.accept();
		
		//分配缓冲区
		ByteBuffer bBuf = ByteBuffer.allocate(1024);
		
		//接收客户端的数据并保存到本地
		while(sChannel.read(bBuf) != -1){
			bBuf.flip();
			fChannel.write(bBuf);
			bBuf.clear();
		}
		ssChannel.close();
		sChannel.close();
		fChannel.close();
	}

	
	//有反馈的客户端和服务端
	@Test
	public void client2() throws Exception{
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		FileChannel fChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		while(fChannel.read(buf) != -1){
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		//告知服务端数据发送完毕，这个不写的话服务端一直在等待客户端发送信息,阻塞式的通信
		sChannel.shutdownOutput();
		//接收反馈
		int len = 0;
		while((len = sChannel.read(buf)) != -1){
			buf.flip();
			System.out.println(new String(buf.array(), 0, len));
			buf.clear();
		}
		
		sChannel.close();
		fChannel.close();
	}

	@Test
	public void server2() throws Exception{
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.bind(new InetSocketAddress(9898));
		FileChannel fChannel = FileChannel.open(Paths.get("6.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		SocketChannel sChannel = ssChannel.accept();
		
		while(sChannel.read(buf) != -1){
			buf.flip();
			fChannel.write(buf);
			buf.clear();
		}
		
		//发送反馈
		buf.put("服务端接收数据成功".getBytes());
		sChannel.write(buf);
		
		sChannel.close();
		ssChannel.close();
		fChannel.close();
	}
}
