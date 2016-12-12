package com.ancs.fileTransport.client;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClient {

	public static void main(String args[]){
		Long begin = System.currentTimeMillis();
		File file = new File("/Users/log/Downloads/test2");
		//File file = new File("C:\\Users\\surface\\Pictures\\Screenshots\\ueditor.parse.js");
		FileClient client = SingleFileClient.getInstance();
		ExecutorService exs =	Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		try {
			for(File childFile:file.listFiles()){
				if(childFile.isFile()&&(!childFile.isHidden())){
					SendFileInThread thread = new SendFileInThread(childFile.getAbsolutePath(), client);
					exs.execute(thread);
				}
			}
		 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exs.shutdown();
	}
}
