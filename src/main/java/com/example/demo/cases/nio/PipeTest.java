package com.example.demo.cases.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

public class PipeTest {

	public static void main(String[] args) throws Exception {
		PipeTest();
	}

	private static void PipeTest() throws Exception {
		final Pipe pipe = Pipe.open();
		final ByteBuffer buf = ByteBuffer.allocate(1024);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				SinkChannel sChannel = pipe.sink();
				buf.put("你好".getBytes());
				buf.flip();
				try {
					sChannel.write(buf);
					buf.clear();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						sChannel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				SourceChannel sChannel = pipe.source();
				try {
					sChannel.read(buf);
					buf.flip();
					System.out.println(new String(buf.array(), 0, buf.limit()));
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		t1.start();
		t1.join();
		t2.start();
		t2.join();
	}
}
