package com.ancs.fileTransport.server;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.ancs.fileTransport.beans.FilePackageBean;

import io.netty.util.concurrent.DefaultEventExecutorGroup;

public class FileOutThreadPool {

	private static ExecutorService  exs ;

	private FileOutThreadPool() {
		super();
		//exs = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	public static Future<FilePackageBean> 
			execute(Callable<FilePackageBean> task){
		if(null==exs)
			//exs = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			exs = new DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors());
			System.out.println(task);
		return exs.submit(task);
	}
}
