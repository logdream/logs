package com.ancs.fileTransport.client;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ClientRunnable extends Thread {

	private File file;
	private FileClient client;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public FileClient getClient() {
		return client;
	}

	public void setClient(FileClient client) {
		this.client = client;
	}

	public ClientRunnable(File file, FileClient client) {
		super();
		this.file = file;
		this.client = client;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if ("4屏幕快照 2016-10-25 17.32.53.png".equals(file.getName())) {
				long timeout = new Random().nextInt(10000);
				System.out.println(timeout);
				Thread.sleep(10000);
			}

			System.out.println(file.getName());
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
