package com.example.lib;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("local host:"+addr);

        //创建一个ServerSocket,用于监听客户端socket的连接请求
        ServerSocket receiver = new ServerSocket(9000);
        //采用循环不断接受来自客户端的请求,服务器端也对应产生一个Socket
        while (true) {

            Socket s = null;
            try {
                System.out.println("等待 输出。。。");
                s = receiver.accept();
                System.out.println("有人偷偷来看你");
            } catch (IOException e) {
                e.printStackTrace();
            }


            OutputStream os = s.getOutputStream();
            os.write("刘小甜来啦，大家快跑啊！\n".getBytes("utf-8"));
            os.close();
            s.close();
        }

    }
}

