package com.ancs.fileTransport.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

public class FileServer {
	public void start(int port) throws Exception {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(); 
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		EventExecutorGroup e1 = new DefaultEventExecutorGroup(8);
		EventExecutorGroup e2 = new DefaultEventExecutorGroup(8);
		try {
			ServerBootstrap b = new ServerBootstrap(); 
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) 
			.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new FileServerInitializer(e1,e2))
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
		Integer port =  (null!=args&&args.length>0)?(Integer.parseInt(args[0])):8000;
		server.start(port);
	}
}
