package com.ancs.fileTransport.server;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ancs.fileTransport.beans.FilePackageBean;


public class FileOutThreadPool {

	private static ExecutorService exs ;

	private FileOutThreadPool() {
		super();
		//exs = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	public static Future<FilePackageBean> 
			execute(Callable<FilePackageBean> task){
		if(null==exs)
			exs = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			//exs = new DefaultEventExecutorGroup(Runtime.getRuntime().availableProcessors());
		return exs.submit(task);
	}
}
