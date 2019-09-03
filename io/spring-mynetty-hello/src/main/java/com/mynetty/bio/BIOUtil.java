package com.mynetty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class BIOUtil {

    public static void close(BufferedReader in, PrintWriter out, Socket socket){
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            in = null;

        }

        if (out != null) {

            out.close();
            out = null;

        }

        if (socket != null) {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = null;

        }
    }
}
