package com.ancs.fileTransport.client;

import java.io.File;

public class SendFileInThread extends Thread{

	private String file;
	private FileClient client;
	public SendFileInThread(String file, FileClient client) {
		super();
		this.file = file;
		this.client = client;
	}
	public void run(){
		try{
			client.sendFile(new File(file));
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
