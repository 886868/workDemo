package com.mrle.ch4.jdk;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyOioServer {
    public void serve(int port) throws InterruptedException {
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));

        EventLoopGroup group = new OioEventLoopGroup();

        try{
            // 创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(OioServerSocketChannel.class)  // 使用OioEventLoopGroup 允许阻塞模式
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 指定ChannelInitializer，对于每个已接收的链接都调用他
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 添加一个ChannelInboundHandlerAdapter 已拦截和处理事件
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate())
                                        .addListener(ChannelFutureListener.CLOSE); // 将消息写到客户端，并添加ChannelFutureListener，以便消息一被写完就关闭链接

                                }
                            });
                        }
                    });
            // 绑定服务器以接受链接
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } finally {
            // 释放所有的资源
            group.shutdownGracefully().sync();
        }

    }
}
