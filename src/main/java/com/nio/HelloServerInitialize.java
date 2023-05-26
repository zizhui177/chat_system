package com.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInitialize extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel){
        //获取channel中的管道
        ChannelPipeline channelPipeline = channel.pipeline();
        //通过福安到，添加handler,编码，解码
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        //添加自定义助手类
        channelPipeline.addLast("CustomerHandler",new CustomerHandler());

    }
}
