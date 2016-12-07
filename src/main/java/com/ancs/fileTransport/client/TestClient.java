package com.ancs.fileTransport.client;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClient {

	public static void main(String args[]){
		Long begin = System.currentTimeMillis();
		File file = new File("/Users/log/Downloads");
		//File file = new File("C:\\Users\\surface\\Pictures\\Screenshots\\ueditor.parse.js");
		String[] destServer = {"127.0.0.1:8000"};
		FileClient client = new FileClient(destServer);
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService exs =	Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		client.start();
		try {
			for(File childFile:file.listFiles()){
				if(childFile.isFile()){
					SendFileInThread thread = new SendFileInThread(childFile.getAbsolutePath(), client);
					exs.execute(thread);
				}
			}
		 
			System.out.println(System.currentTimeMillis()-begin);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
