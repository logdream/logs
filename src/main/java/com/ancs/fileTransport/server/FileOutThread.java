package com.ancs.fileTransport.server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.ancs.fileTransport.beans.FilePackageBean;

public class FileOutThread extends Thread{

	private FilePackageBean fileBean;
	private String baseDir;
	
	public FileOutThread(FilePackageBean fileBean, String baseDir) {
		super();
		this.fileBean = fileBean;
		this.baseDir = baseDir;
	}

	@Override
	public void run(){
		String filePath = baseDir + File.separator+fileBean.getUuid()+File.separator+fileBean.getFileName();
		try {
			File file = new File(filePath);
			if(!file.exists()){
				File parent = new File(file.getParent());
				if(!parent.exists()){
					parent.mkdirs();
				}
				
				synchronized (parent) {
					file.createNewFile();	
				}
				
			}
			RandomAccessFile rfile = new RandomAccessFile(filePath,"rw");
			if(rfile.length()!=fileBean.getFileSize()){
				synchronized (rfile) {
					rfile.setLength(fileBean.getFileSize());
				}
			
			}
			long offset = FilePackageBean.partSize*fileBean.getIndex();
			System.out.println(fileBean);
			System.out.println("the position is "+offset+" and the index is "+fileBean.getIndex());
			ByteBuffer buffer = ByteBuffer.allocate(fileBean.getCsize());
			buffer.put(fileBean.getContent());
			
			FileChannel channel = rfile.getChannel();
			
					channel.write(buffer, offset);
					channel.force(true);
					channel.close();
			//去掉引用
			rfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
