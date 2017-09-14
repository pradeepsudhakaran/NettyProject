package com.netty.messanger;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ClientMessangerHandler extends ChannelInboundHandlerAdapter {
	

	public void messageReceived(ChannelHandlerContext ctx, String inputString) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("String from" + inputString + "\n");
		
	}
}
