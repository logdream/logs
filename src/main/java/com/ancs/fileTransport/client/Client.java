package com.ancs.fileTransport.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Client {
	public void connect(String host, int port) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap(); 
			b.group(workerGroup); 
			b.channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)); 
			b.option(ChannelOption.SO_KEEPALIVE, true); 
			b.handler(new ClientInitializer());
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
			
			
		} finally {
			workerGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.connect("127.0.0.1", 8000);
	}
}
