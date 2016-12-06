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
	final static String winTemp = "f:/test/";
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		FilePackageBean bean = (FilePackageBean)msg;
		FilePackageBean newone = (FilePackageBean) bean.clone();
		logger.info(bean.toString());
//		File dir = new File(temp+File.separator+bean.getUuid());
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		
//		File file = new File(dir.getAbsolutePath()+File.separator+bean.getIndex()+"_"+bean.getFileName());
//		OutputStream outputStream = new FileOutputStream(file);
//		IOUtils.write(bean.getContent(), outputStream);
//		outputStream.close();
//		bean.setContent(null);
//		System.out.println(System.currentTimeMillis());
		FileOutThread thread = new FileOutThread(newone, winTemp);
		//thread.start();
		System.out.println("1111"+thread);
		ctx.flush();
		FileOutThreadPool.execute(thread, null);
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
