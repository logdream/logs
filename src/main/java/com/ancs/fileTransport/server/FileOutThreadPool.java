package com.ancs.fileTransport.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ancs.fileTransport.server.intfaces.CallBackInfo;

public class FileOutThreadPool {

	private static ExecutorService  exs ;

	private FileOutThreadPool() {
		super();
		exs = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	public static void execute(Runnable task,CallBackInfo back){
		if(null==exs)
			exs = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		System.out.println(task);
		exs.execute(task);
		if(null!=back)
			back.callback("done");
	}
}
