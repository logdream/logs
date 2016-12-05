package com.ancs.fileTransport.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BusinessHandler extends ChannelInboundHandlerAdapter {

	final static String temp = "/Users/log/Desktop/test";
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(System.currentTimeMillis());
		FilePackageBean bean = (FilePackageBean)msg;
		
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
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
		cause.printStackTrace();
    }
}
