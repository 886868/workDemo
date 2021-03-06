package com.mrle.ch4.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioServer {
    public void serve(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        ServerSocket ssocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        // 将服务器绑定到指定端口
        ssocket.bind(address);
        // 打开Selector来处理Channel
        Selector selector = Selector.open();
        // 将ServerSocket 注册到Selector以接受链接
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        while (true) {
            try {
                // 等待需要处理的新事件；
                // 阻塞将一直持续到下一个传入事件
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
            // 获取所有接受事件的SelectionKey 实例
            Set<SelectionKey> readKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    // 检查事件是否是一个新的已经就绪可以被接受的链接
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        // 接受客户端并将它注册到选择器
                        client.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("Accepted connection from " + client);
                    }
                    // 检查套接字是否已经准备好写数据
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        while (buffer.hasRemaining()) {
                            // 将数据写到已连接的客户端
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        // 关闭链接
                        client.close();
                    }
                } catch (IOException ex) {
                    key.cancel();
                    key.channel().close();
                }
            }
        }

    }
}
