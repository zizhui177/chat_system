package com.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {
    public static void main(String[] args){
        EventLoopGroup mainGroup = new NioEventLoopGroup();//主池
        EventLoopGroup workGroup = new NioEventLoopGroup();//工作池
        try{
            //建立netty服务器启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                                                   .group(mainGroup,workGroup)
                                                   .channel(NioServerSocketChannel.class)
                                                   .childHandler(new HelloServerInitialize());
            //启动server
            ChannelFuture channelFuture =  serverBootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
             mainGroup.shutdownGracefully();
             workGroup.shutdownGracefully();
        }

    }
}
