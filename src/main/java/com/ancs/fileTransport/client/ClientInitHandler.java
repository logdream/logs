package com.ancs.fileTransport.client;

import java.io.File;

import com.ancs.fileTransport.beans.FilePackageBean;
import com.ancs.fileTransport.beans.STATUS;
import com.ancs.fileTransport.beans.TYPE;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientInitHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(1111111111);
		File file = new File("/Users/log/Desktop/poi-3.13.jar");
		FilePackageBean bean = new FilePackageBean(file);
		while (bean.hasNext()) {
			FilePackageBean bean2 = bean.next();
			bean2.setType(TYPE.SEND);
			bean2.setStatus(STATUS.SUCCESS);
			ctx.write(bean2);
			ctx.flush();

		}
		System.out.println(1111111111);
	}
}
