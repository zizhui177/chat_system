package com.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WSServer {
    public static void main(String[] args){
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap wsServer = new ServerBootstrap();

        try{
            wsServer.group(mainGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WSServerInitialize());
            ChannelFuture channelFuture = wsServer.bind(8089).sync();
            channelFuture.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            mainGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
