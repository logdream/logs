package com.dream.logs.tests;

import java.io.File;

import com.ancs.fileTransport.beans.FilePackageBean;
import com.ancs.fileTransport.server.FileOutThread;

public class RandomFileTest {

	public static void main(String args[]) throws Exception{
		File file = new File("/Users/log/Desktop/poi-3.13.jar");
		FilePackageBean bean = new FilePackageBean(file);
		while (bean.hasNext()) {
			FilePackageBean bean2 = bean.next();
			FileOutThread outThread = new FileOutThread(bean2, "/Users/log/Desktop/test");
			//outThread.start();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
