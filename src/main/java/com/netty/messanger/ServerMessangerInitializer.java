package com.netty.messanger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerMessangerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		
ChannelPipeline pipeLine = socketChannel.pipeline();
		
		pipeLine.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeLine.addLast("decoder",new StringDecoder());
		pipeLine.addLast("encoder", new StringEncoder());
		
		pipeLine.addLast("handler",new ServerMessangerHandler());
	}

}
