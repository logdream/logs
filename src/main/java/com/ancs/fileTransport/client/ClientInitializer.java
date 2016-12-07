package com.ancs.fileTransport.client;

import com.ancs.fileTransport.coder.DecoderBean;
import com.ancs.fileTransport.coder.EncoderBean;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class ClientInitializer extends ChannelInitializer<Channel>{

	@Override
	protected void initChannel(Channel ch) throws Exception {
		// TODO Auto-generated method stub
		ch.pipeline().addLast(new DecoderBean());
		ch.pipeline().addLast(new EncoderBean());
		ch.pipeline().addLast(new ClientInitHandler());
	}

}
