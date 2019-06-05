package com.example.demo.cases.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-6-5 13:46
 * 4
 */
public class NioTest {

    @Test
    public void testNio(){
        String str = "abc";
        ByteBuffer buf = ByteBuffer.allocate(5);
        System.out.println("allocate-----");
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());

        System.out.println("put-----");
        buf.put(str.getBytes());
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());

        buf.flip();
        System.out.println("flip-----");
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());

        byte[] b = new byte[buf.limit()];
        buf.get(b);
        System.out.println("get-----");
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());

        buf.rewind();
        System.out.println("rewind-----");
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());
        buf.clear();

        System.out.println("mark-----reset");
        b = new byte[buf.limit()];
        buf.get(b, 0, 1);
        System.out.println(new String(b));
        buf.mark();
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());
        b = new byte[buf.limit()];
        buf.get(b, 0, 1);
        System.out.println(new String(b));
        buf.reset();
        System.out.println("capacity:"+buf.capacity()+"--limit:"+buf.limit()+"--position:"+buf.position());
    }
}
