package com.ancs.fileTransport.coder;


import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.handler.codec.MessageToByteEncoder;

public class EncoderBean extends MessageToByteEncoder<FilePackageBean>{

	 
	@Override
	protected void encode(ChannelHandlerContext ctx, FilePackageBean msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		
		int size = 60 + msg.getCsize();
		ByteBuf bt = ctx.alloc().buffer(size);
		bt.writeByte(msg.getType().getByteValue());
		bt.writeByte(msg.getStatus().getByteValue());
		bt.writeInt(msg.getTotal());
		bt.writeInt(msg.getIndex());
		bt.writeInt(msg.getCsize());
		bt.writeBytes(msg.getUuid().getBytes());
		bt.writeBytes(msg.getContent());
		ctx.flush();
	}

}
