package com.ancs.fileTransport.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class BusinessHandler extends ChannelInboundHandlerAdapter {
	private static final InternalLogger logger =
            InternalLoggerFactory.getInstance(BusinessHandler.class);
	final static String temp = "/Volumes/EFI/test";
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		FilePackageBean bean = (FilePackageBean)msg;
		logger.info(bean.getFileName()+":"+bean.getIndex());
		File dir = new File(temp+File.separator+bean.getUuid());
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		File file = new File(dir.getAbsolutePath()+File.separator+bean.getIndex()+"_"+bean.getFileName());
		OutputStream outputStream = new FileOutputStream(file);
		IOUtils.write(bean.getContent(), outputStream);
		System.out.println(System.currentTimeMillis());
	}
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
		cause.printStackTrace();
    }
}
