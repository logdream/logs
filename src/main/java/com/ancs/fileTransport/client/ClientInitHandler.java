package com.ancs.fileTransport.client;

import com.ancs.fileTransport.beans.FilePackageBean;
import com.ancs.fileTransport.server.FileOutThread;
import com.ancs.fileTransport.server.FileOutThreadPool;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class ClientInitHandler extends ChannelInboundHandlerAdapter {
	private static final InternalLogger logger =
            InternalLoggerFactory.getInstance(ClientInitHandler.class);
	final static String temp = "/Volumes/EFI/test";
	final static String winTemp = "f:/test/";
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		FilePackageBean bean = (FilePackageBean)msg;
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
		
		//thread.start();
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
