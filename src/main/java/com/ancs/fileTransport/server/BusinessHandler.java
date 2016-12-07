package com.ancs.fileTransport.server;

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
		FileOutThread thread = new FileOutThread(newone, temp);
		java.util.concurrent.Future<FilePackageBean> beans = FileOutThreadPool.execute(thread);
		System.out.println(beans.get().getType());
		//ctx.channel().
		//ctx.writeAndFlush(beans.get());
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
