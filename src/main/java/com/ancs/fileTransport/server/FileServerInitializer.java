package com.ancs.fileTransport.server;

import com.ancs.fileTransport.coder.DecoderBean;
import com.ancs.fileTransport.coder.EncoderBean;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.util.concurrent.EventExecutorGroup;

public class FileServerInitializer extends ChannelInitializer<Channel> {

	private EventExecutorGroup e1;
	private EventExecutorGroup e2;

	public FileServerInitializer(EventExecutorGroup e1, EventExecutorGroup e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// TODO Auto-generated method stub
		ch.pipeline().addLast(new EncoderBean());
		ch.pipeline().addLast(e1, new DecoderBean());
		ch.pipeline().addLast(e1, new FileOutHandler());
		ch.pipeline().addLast(e2, new ReplayHandler());
	}

}
