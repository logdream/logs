package com.ancs.fileTransport.client;

import java.io.File;

import com.ancs.fileTransport.beans.FilePackageBean;
import com.ancs.fileTransport.beans.STATUS;
import com.ancs.fileTransport.beans.TYPE;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientInitHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(1111111111);
		
		
	}
}
