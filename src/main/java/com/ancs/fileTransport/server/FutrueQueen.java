package com.ancs.fileTransport.server;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import com.ancs.fileTransport.beans.FilePackageBean;

public class FutrueQueen {

	private static LinkedBlockingQueue<Future<FilePackageBean>> Queue;
	
	public void addOne(Future<FilePackageBean> newOne){
		if(null==Queue)
			Queue = new LinkedBlockingQueue<Future<FilePackageBean>>();
		Queue.add(newOne);
	}
	
}
