package com.ancs.fileTransport.client;

import java.io.File;
import java.io.IOException;

public class TestClient {

	public static void main(String args[]){
		
		File file = new File("/Users/log/Documents/VMware-Fusion-8.5.0-4352717.dmg");
		String[] destServer = {"127.0.0.1:8000"};
		FileClient client = new FileClient(destServer);
		client.start();
		try {
			client.sendFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
