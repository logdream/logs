package com.ancs.fileTransport.client;


import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientInitHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		FilePackageBean bean = new FilePackageBean(1, 1, "hello world".getBytes());
		ctx.write(bean);
		ctx.flush();
	}
}
