package com.mynetty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int DEFAULT_PORT = 7777;

    private static ServerSocket serverSocket;

    public static  void start() throws  Exception{
        doStart(DEFAULT_PORT);
    }

    private synchronized  static void doStart(int port) throws IOException {
        if (null != serverSocket){
            return;
        }
        serverSocket = new ServerSocket(port);
        while (true){
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        }
    }

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
