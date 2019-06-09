package com.example.demo.cases.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Pipe.SinkChannel;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;


public class NonBlockingNIOTest {
	// 非阻塞式
	@Test
	public void client() throws Exception {
		// 获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

		// 切换非阻塞模式
		sChannel.configureBlocking(false);

		// 获取缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);

		// 发送数据
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			buf.put((new Date().toString()+"\n"+sc.next()).getBytes());
			buf.flip();
			sChannel.write(buf);
			buf.clear();
//			int len = 0;
//			while((len = sChannel.read(buf)) > 0){
//				buf.flip();
//				System.out.println(new String(buf.array(), 0, len));
//				buf.clear();
//			}
		}
		// 关闭通道
		sChannel.close();
	}

	@Test
	public void server() throws Exception {
		// 1.获得通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		// 2.切换非阻塞模式
		ssChannel.configureBlocking(false);
		// 3.绑定端口
		ssChannel.bind(new InetSocketAddress(9898));
		// 4.获取选择器
		Selector selector = Selector.open();
		// 5.将通道注册到选择器上
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 6.轮询式的获取选择器上"准备就绪"的事件
		while (selector.select() > 0) {//selector.select()阻塞直到有访问进来
			// 7.获取当前选择器中所有注册的选择键(已就绪的监听事件)
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				// 8.获取准备就绪的事件
				SelectionKey sk = it.next();
				// 9.判断具体是什么事件准备就绪
				if (sk.isAcceptable()) {
					// 10.若就绪则获得客户端连接
					SocketChannel sChannel = ssChannel.accept();
					// 11.切换非阻塞模式
					sChannel.configureBlocking(false);
					// 12.将该通道注册到选择器上
					sChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
					//客户端<-----------------服务器端，read和write的目标是客户端
				} else if (sk.isReadable()) {//即客户端有数据发过来了
					// 13.获取当前选择器上"读就绪"状态的通道
					SocketChannel sChannel = (SocketChannel) sk.channel();
					// 14.读取数据
					ByteBuffer buf = ByteBuffer.allocate(1024);

					int len = 0;
					while ((len = sChannel.read(buf)) > 0) {
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}
					
					buf.put("发送成功".getBytes());
					buf.flip();
					sChannel.write(buf);
					buf.clear();
				} 
//				else if(sk.isWritable()){
//					SocketChannel sChannel = (SocketChannel)sk.channel();
//					ByteBuffer buf = ByteBuffer.allocate(1024);
//					buf.put("发送成功".getBytes());
//					buf.flip();
//					sChannel.write(buf);
//					buf.clear();
//				}
			}
			// 15.取消选择键
			it.remove();
		}
		selector.close();
	}
	
	@Test
	public void client1() throws Exception{
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		sChannel.configureBlocking(false);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			buffer.put((new Date()+"\n"+scanner.next()).getBytes());
			buffer.flip();
			sChannel.write(buffer);
			buffer.clear();
		}
		sChannel.close();
	}
	
	@Test
	public void server1() throws Exception{
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.bind(new InetSocketAddress(9898));
		ssChannel.configureBlocking(false);
		Selector selector = Selector.open();
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(selector.select() > 0){
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey sk = it.next();
				if(sk.isAcceptable()){
					SocketChannel sChannel = ssChannel.accept();
					sChannel.configureBlocking(false);
					sChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
				}else if(sk.isReadable()){
					SocketChannel sChannel = (SocketChannel)sk.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					while(sChannel.read(buffer) > 0){
						buffer.flip();
						System.out.println(new String(buffer.array(), 0, buffer.limit()));
						buffer.clear();
					}
				}
			}
			it.remove();
		}
		selector.close();
	}
	//----------------------------------------------------------------
	
	@Test
	public void client2() throws Exception{
		DatagramChannel dc = DatagramChannel.open();
		dc.configureBlocking(false);
		ByteBuffer buf = ByteBuffer.allocate(1024);
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			buf.put((new Date().toString()+"\n"+sc.next()).getBytes());
			buf.flip();
			dc.send(buf, new InetSocketAddress("127.0.0.1", 9898));
			buf.clear();
		}
		dc.close();
	}
	
	@Test
	public void server2() throws Exception{
		DatagramChannel dc = DatagramChannel.open();
		dc.configureBlocking(false);
		dc.bind(new InetSocketAddress(9898));
		Selector selector = Selector.open();
		dc.register(selector, SelectionKey.OP_READ);
		while(selector.select() > 0){
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey sk = it.next();
				if(sk.isReadable()){
					ByteBuffer buf = ByteBuffer.allocate(1024);
					dc.receive(buf);
					System.out.println(new String(buf.array(), 0, buf.limit()));
				}
			}
			it.remove();
		}
		selector.close();
	}
	
	@Test
	public void pipe() throws Exception{
		Pipe pipe = Pipe.open();
		SinkChannel sinkChannel = pipe.sink();
		ByteBuffer buf = ByteBuffer.allocate(1024);

		buf.put("你好啊".getBytes());
		buf.flip();
		sinkChannel.write(buf);
		
		Pipe.SourceChannel sourceChannel = pipe.source();
		sourceChannel.read(buf);
		System.out.println(new String(buf.array(), 0, buf.limit()));
		buf.clear();
		sinkChannel.close();
		sourceChannel.close();
	}
}
