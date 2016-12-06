package com.ancs.fileTransport.coder;


import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class EncoderBean extends MessageToByteEncoder<FilePackageBean>{

	
	@Override
	protected void encode(ChannelHandlerContext ctx, FilePackageBean msg, ByteBuf bt) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(msg.toString());
		bt.writeInt(msg.getIndex());
		bt.writeByte(msg.getType().getByteValue());
		bt.writeByte(msg.getStatus().getByteValue());
		bt.writeLong(msg.getFileSize());
		bt.writeInt(msg.getTotal());
		bt.writeBytes(msg.getUuid().getBytes());
		bt.writeInt(msg.getNameSize());
		bt.writeBytes(msg.getFileName().getBytes());
		bt.writeInt(msg.getCsize());
		bt.writeBytes(msg.getContent());
		ctx.flush();
	}

}
