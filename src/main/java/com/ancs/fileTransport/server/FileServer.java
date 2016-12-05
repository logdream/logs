package com.ancs.fileTransport.server;


import com.ancs.fileTransport.coder.DecoderBean;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class FileServer {
	public void start(int port) throws Exception {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(); 
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap(); 
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) 
			.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() { 
								@Override
								public void initChannel(SocketChannel ch) throws Exception {
									ch.pipeline().addLast(new DecoderBean());
									ch.pipeline().addLast(new BusinessHandler());
								}
							})
					
					.childOption(ChannelOption.SO_KEEPALIVE, true); 
			
			ChannelFuture f = b.bind(port).sync(); 

			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		FileServer server = new FileServer();
		server.start(8000);
	}
}
