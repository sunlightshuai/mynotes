package com.mynetty.nio.channel;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
 
public class FileChannelDemo {
     
    public static void main(String[] args) {

        String fileName = "F:/tmp/nio.data";


        try {
            File file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            FileChannel fc = fos.getChannel();

            ByteBuffer bb = ByteBuffer.allocate(64);

            bb.put("hello world 123 \n".getBytes("UTF-8"));
            bb.flip();

            fc.write(bb);

            bb.clear();

            bb.put("你好，世界".getBytes("UTF-8"));
            bb.flip();

            fc.write(bb);
            bb.clear();

            fos.close();
            fc.close();
        }catch (IOException e){
            e.printStackTrace();
        }



        try {
            Path path = Paths.get(fileName);
            FileChannel fc = FileChannel.open(path);
            ByteBuffer bb = ByteBuffer.allocate((int)fc.size()+1);
            Charset utf8 = Charset.forName("UTF-8");
            fc.read(bb);
            bb.flip();
            CharBuffer cb = utf8.decode(bb);
            System.out.println(cb.toString());
            bb.clear();
            fc.close();

        }catch (IOException e){
            e.printStackTrace();
        }



    }
}
 