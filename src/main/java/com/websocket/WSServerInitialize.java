package com.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSServerInitialize extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel){
        ChannelPipeline pipeline = channel.pipeline();
        //wenSocekt基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        //对所有httpMessage进行聚合
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        //webSocket需要的handler
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义
        pipeline.addLast(new ChatHandler());
    }
}
