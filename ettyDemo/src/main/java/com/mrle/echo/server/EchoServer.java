package com.mrle.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        /*if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
            return;
        }
*/
//        int port = Integer.parseInt(args[0]);

        int port = 8888;
        new EchoServer(port).start();
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            // 创建 ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class) // 指定使用的 NIO 传输 Channel
                    .localAddress(new InetSocketAddress(port)) // 使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 添加一个EchoServerHandler 到子 Channel的 ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // EchoServerHandler被标记为 @Shareable, 所以我们可以总是使用同样的实例
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步绑定服务器；调用 sync 方法阻塞等待知道绑定完成；
            ChannelFuture f = b.bind().sync();
            // 获取 Channel 的 CloseFuture, 并且阻
            // 塞当前线程知道它完成
            System.out.println("closeFuture before!");
            f.channel().closeFuture().sync();
            System.out.println("closeFuture after!");
        } finally{
            // 关闭 EventLoopGroup, 释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

}
