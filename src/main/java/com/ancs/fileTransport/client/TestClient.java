package com.ancs.fileTransport.client;

import java.io.File;

public class TestClient {

	public static void main(String args[]){
		
		File file1 = new File("/Users/log/Desktop/4屏幕快照 2016-10-25 17.32.53.png");
		File file2 = new File("/Users/log/Desktop/catalina.out");
		File file3 = new File("/Users/log/Desktop/catalina.log");
		File file4 = new File("/Users/log/Desktop/poi-3.13.jar");
		String[] destServer = {"127.0.0.1:8000"};
		FileClient client = new FileClient(destServer);
		client.start();
		ClientRunnable c1 = new ClientRunnable(file1, client);
		ClientRunnable c2 = new ClientRunnable(file2, client);
		ClientRunnable c3 = new ClientRunnable(file3, client);
		ClientRunnable c4 = new ClientRunnable(file4, client);
		try {
			c1.start();
			c2.start();
			c3.start();
			c4.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
