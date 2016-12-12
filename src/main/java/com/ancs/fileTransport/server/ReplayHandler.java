package com.ancs.fileTransport.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class ReplayHandler  extends ChannelInboundHandlerAdapter{
	private static final InternalLogger logger =
            InternalLoggerFactory.getInstance(ReplayHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info(msg.toString());
		ctx.channel().writeAndFlush(msg);
		//ctx.channel().
		//ctx.writeAndFlush(beans.get());
	}
}
