package com.ancs.fileTransport.utils;

import java.io.File;

public class FileToPackage {

	private File fromFile; //原始文件
	private Long fileSize;
	private Integer total;
	private Integer index;
	private final static long partSize = 1024*63-60;//分块大小；
	
	
	
	
	public FileToPackage(File fromFile) {
		super();
		this.fromFile = fromFile;
		this.fileSize = fromFile.length();
		this.index = 0;
		this.total = Integer.parseInt((fromFile.length()/partSize+1)+"");
	}




	@Override
	public String toString() {
		return "FileToPackage [fromFile=" + fromFile + ", fileSize=" + fileSize + ", total=" + total + ", index="
				+ index + "]";
	}




	public static void main(String args[]){
		System.out.println(Long.MAX_VALUE/partSize);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Long.MAX_VALUE/Integer.MAX_VALUE/1024/1024);
		File file = new File("/Users/log/Documents/vm/xubuntu.vhd");
		System.out.println(file.length());
		FileToPackage fileToPackage = new FileToPackage(file);
		System.out.println(fileToPackage.toString());
	}
	
	
}
