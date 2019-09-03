package com.mynetty.nio.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferDemo {

    public static char[] decode(String str) throws UnsupportedEncodingException {

        if (null == str || "".equals(str)){
            return null;
        }
        // System.out.println("str.length:"+str.getBytes().length);
        // ByteBuffer byteBuffer = ByteBuffer.allocate(str.getBytes().length);
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(str.getBytes("UTF-8"));
        byteBuffer.flip();

        Charset utf8 = Charset.forName("UTF-8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);
        char[] charArr = Arrays.copyOf(charBuffer.array(),charBuffer.limit());
        System.out.println("decode-charArr:"+new String(charArr));
        return charArr;

    }

    public static byte[] encode(String str){
        if (null == str || "".equals(str)){
            return null;
        }
        CharBuffer charBuffer = CharBuffer.allocate(str.getBytes().length);
        charBuffer.append(str);
        charBuffer.flip();

        Charset utf8 = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer);
        byte[] bytes = Arrays.copyOf(byteBuffer.array(),byteBuffer.limit());
        return bytes;

    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        BufferDemo.decode("法不外求,反求诸己");
        System.out.println(BufferDemo.encode("法不外求,反求诸己"));
        BufferDemo.decode(new String(BufferDemo.encode("法不外求,反求诸己")));
    }
}
