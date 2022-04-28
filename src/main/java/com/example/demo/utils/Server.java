package com.example.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yzk
 * @version 1.0
 * @desc
 * @since 2022/4/28 10:34
 **/

public class Server {

    public static void main(String[] args) throws IOException {

        //1.开启服务器，参数是指开着的端口号
        ServerSocket server = new ServerSocket(8899);
        System.out.println("服务器已成功启动");
        //2.接收客户端发来的连接
        Socket socket = server.accept();
        System.out.println("接收到客户端发来的请求");
        //3.获取读取流
        InputStream in = socket.getInputStream();
        //4.读取数据
//        int data = in.read();      //默认返回的是整数
        for (int i = 0; i < 5; i++) {
            char data = (char) in.read();
            System.out.print(data);
        }
        //5.给客户端写出数据
        System.out.println();
        OutputStream out = socket.getOutputStream();
        out.write("world".getBytes());
        System.out.println("服务器端成功发送数据");
        out.close();
    }
}
