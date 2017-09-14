package com.netty.messanger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerMessanger {

	private final int port;
	
	public ServerMessanger(int port){
		this.port=port;
	}
	
	public void run(){
		EventLoopGroup bossGroup = null;
		EventLoopGroup workerGroup = null;
		ServerBootstrap serverBootStrap = null;
		try{
			bossGroup = new NioEventLoopGroup();
			workerGroup = new NioEventLoopGroup();
			
			serverBootStrap = new ServerBootstrap()
					.group(bossGroup,workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							ChannelPipeline pipeLine = socketChannel.pipeline();
							pipeLine.addLast(new ServerMessangerHandler());
						}
						
					});
			
			ChannelFuture channelFuture =  serverBootStrap.bind(port).sync();
			
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String args[]){
		new ServerMessanger(8080).run();
	}
}
