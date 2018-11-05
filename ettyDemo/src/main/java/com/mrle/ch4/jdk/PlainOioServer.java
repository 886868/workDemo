// 使用 jdk 的同步socket server
package com.mrle.ch4.jdk;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainOioServer {
    public void serve(int port) throws IOException {

        // 将服务器绑定到指定端口
        final ServerSocket socket = new ServerSocket(port);
        try{
            while (true) {
                // 接受客户端的链接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                // 创建一个新的线程来处理该链接
                new Thread(() -> {
                    OutputStream out;
                    try {
                        out = clientSocket.getOutputStream();
                        // 将消息写给自己链接的客户端
                        out.write("Hi \r\n".getBytes(CharsetUtil.UTF_8));
                        out.flush();
                        // 关闭链接
                        clientSocket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally{
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start(); // 启动线程
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
