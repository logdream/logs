package com.ancs.fileTransport.server;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class FileOutHandler extends ChannelInboundHandlerAdapter {
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(FileOutHandler.class);
	final static String temp = "/Volumes/EFI/test";
	final static String winTemp = "f:/test/";
	private static Lock lock = new ReentrantLock();
	private static Long begin;
	@Override
	 public void channelActive(ChannelHandlerContext ctx) throws Exception {
		begin = System.currentTimeMillis();
	};
	@Override 
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("时间是："+(System.currentTimeMillis()-begin));
		
	};
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		 
			FilePackageBean bean = (FilePackageBean) msg;
			logger.info(bean.toString());
			FilePackageBean newone = (FilePackageBean) bean.clone();
			logger.info(newone.toString());
			FileOutSync sync = new FileOutSync(newone, temp);
			// Future<FilePackageBean> futurev =
			// FileOutThreadPool.execute(task);
			FilePackageBean mBean = sync.writeToFile();
			ReferenceCountUtil.release(msg); // 2
			ctx.fireChannelRead(mBean);

		// ctx.channel().
		// ctx.writeAndFlush(beans.get());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}
}
