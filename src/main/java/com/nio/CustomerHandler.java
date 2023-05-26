package com.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


public class CustomerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o){
        Channel channel = channelHandlerContext.channel();
        if(o instanceof HttpRequest) {
            System.out.println(channel.remoteAddress());
            //定义发送数据消息的格式
            ByteBuf content = Unpooled.copiedBuffer("Hello netty~", CharsetUtil.UTF_8);
            //构建一个httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //为相应数据添加类型和长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            channelHandlerContext.writeAndFlush(response);
        }

    }
}
