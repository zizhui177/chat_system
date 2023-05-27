package com.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup clients = ( new DefaultChannelGroup(GlobalEventExecutor.INSTANCE));


    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        System.out.println("长id"+ctx.channel().id().asLongText());
        System.out.println("短id"+ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame)  {
       String content = textWebSocketFrame.text();
        System.out.println("接收的数据"+content);
        for(Channel channel:clients){
             channel.writeAndFlush(new TextWebSocketFrame("服务器在 "+ LocalDateTime.now()+"接收到的消息为："+content));

        }
    }
}
