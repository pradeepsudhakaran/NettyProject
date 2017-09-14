package com.netty.messanger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.json.JSONObject;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.util.CharsetUtil;

public class ClientMessanger {
	private final String host;
	private final int port;
	
	private String messengerName;
	private String emailAddress;
	private double phoneNumber;
	
	public String getMessengerName() {
		return messengerName;
	}

	public void setMessengerName(String messengerName) {
		this.messengerName = messengerName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public ClientMessanger(String host,int port){
		this.host=host;
		this.port=port;
	}
	
	public void run(){
		EventLoopGroup eventGroup = null;
		Bootstrap bootStrap = null;
		JSONObject json = null;
		try{
			eventGroup = new NioEventLoopGroup();
			bootStrap = new Bootstrap()
					.group(eventGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new ClientMessangerInitializer() ); 
			
			Channel channel = bootStrap.connect(host,port).sync().channel();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Name: \n");
			setMessengerName(scanner.nextLine());
			
			System.out.println("Enter Email Address: \n");
			setEmailAddress(scanner.nextLine());
			
			System.out.println("Enter Phone Number: \n");
			setPhoneNumber(scanner.nextDouble());
			
			json = new JSONObject();
			
			json.put("name", getMessengerName());
			json.put("e-mail", getEmailAddress());
			json.put("phoneNumber", getPhoneNumber());
			
			ByteBuf buffer = Unpooled.copiedBuffer(json.toString(), CharsetUtil.UTF_8);
			
			channel.write(new DefaultHttpContent(buffer));
			
			/*BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				channel.write(input.readLine() + "IP server");
			}*/
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/finally{
			eventGroup.shutdownGracefully();
		}
	}
	
	public static void main(String agrs[]){
		new ClientMessanger("localhost", 8080).run();
	}
}
