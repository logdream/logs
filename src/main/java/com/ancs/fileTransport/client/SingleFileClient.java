package com.ancs.fileTransport.client;

public class SingleFileClient {

	private static FileClient client;
	private static Integer LL=0;
	public static FileClient getInstance(){
		synchronized (LL) {
			if(null==client){
				String[] destServer = {"127.0.0.1:8000"};
				client = new FileClient(destServer);
				client.start();
			}
		}
		return client;
	}
	
}
