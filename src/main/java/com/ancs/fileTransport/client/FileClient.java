package com.ancs.fileTransport.client;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.ancs.fileTransport.beans.FilePackageBean;
import com.ancs.fileTransport.beans.STATUS;
import com.ancs.fileTransport.beans.TYPE;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class FileClient {
	private static final InternalLogger logger = InternalLoggerFactory.getInstance(FileClient.class);
	private final Bootstrap bootstrap;
	private final EventLoopGroup eventWorkerLoopGroup;
	private String[] destServers;
	private ConcurrentHashMap<String, ChannelFuture> channelMap = new ConcurrentHashMap<>();

	public FileClient(String[] destServers) {
		this.bootstrap = new Bootstrap();
		this.eventWorkerLoopGroup = new NioEventLoopGroup();
		this.destServers = destServers;
	}

	public void start() {
		this.bootstrap.group(eventWorkerLoopGroup).channel(NioSocketChannel.class).handler(new ClientInitializer());
		initChannels();
	}

	/**
	 * 初始化所有的连接
	 */
	private void initChannels() {
		for (String addr : destServers) {
			ChannelFuture future = channelMap.get(addr);
			if (future != null) {
				break;
			}
			String[] split = addr.split(":");
			InetSocketAddress address = new InetSocketAddress(split[0], Integer.valueOf(split[1]));
			ChannelFuture sync = null;
			try {
				sync = this.bootstrap.connect(address).sync();
				channelMap.put(addr, sync);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 建立channel
	 * 
	 * @param addr
	 * @return
	 */
	public Channel createChannel(String addr) {
		Channel channel = null;
		ChannelFuture future = channelMap.get(addr);
		if (future != null) {
			channel = future.channel();
			return channel;
		}

		String[] split = addr.split(":");
		InetSocketAddress address = new InetSocketAddress(split[0], Integer.valueOf(split[1]));
		ChannelFuture sync = null;
		try {
			sync = this.bootstrap.connect(address).sync();
			channelMap.put(addr, sync);
			channel = sync.channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return channel;
	}

	private Channel getRandom() {
		String addr = destServers[new Random().nextInt(this.destServers.length)];
		Channel channel = createChannel(addr);
		return channel;

	}

	public void destroy() { // 4
		eventWorkerLoopGroup.shutdownGracefully();
	}

	public void sendFile(File file) throws IOException, Exception {
		Channel channel = getRandom();
		FilePackageBean bean = new FilePackageBean(file);
		while (bean.hasNext()) {
			FilePackageBean bean2 = bean.next();
			logger.info("开始发送文件：" + bean.getFileName() + ",part:" + bean.getIndex());
			bean2.setType(TYPE.SEND);
			bean2.setStatus(STATUS.SUCCESS);
			channel.writeAndFlush(bean2);
			logger.info("结束文件：" + bean.getFileName());
		}

	}
}
