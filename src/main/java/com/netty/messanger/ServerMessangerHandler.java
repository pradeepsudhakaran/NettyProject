package com.netty.messanger;

import org.json.JSONObject;

import com.kafka.messenger.MessageProducer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ServerMessangerHandler extends ChannelInboundHandlerAdapter {


	public void messageReceived(ChannelHandlerContext ctx, Object messageString) {
		String byteBufMsg=null;
		Channel incomingChannel = null;
		JSONObject requestJSON = null;
		ByteBuf byteBuf =  null;
		MessageProducer messageProducer = null;
		try{
			messageProducer = new MessageProducer("MessangerDetails");
			incomingChannel = ctx.channel();
			byteBuf = (ByteBuf)messageString;
			byteBufMsg = byteBuf.toString(CharsetUtil.UTF_8);
			
			requestJSON = new JSONObject(byteBufMsg);
			messageProducer.run(requestJSON);
			
		}finally{
			byteBuf=null;
			requestJSON=null;
			incomingChannel = null;
			byteBufMsg = null;
		}
		
		
	}

}
