package com.ancs.fileTransport.server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

import com.ancs.fileTransport.beans.FilePackageBean;

public class FileOutThread implements Callable<FilePackageBean> {

	private FilePackageBean fileBean;
	private RandomAccessFile rfile;
	private File destFile;

	public FileOutThread(FilePackageBean fileBean, String baseDir) throws IOException {
		super();
		this.fileBean = fileBean;
		String filePath = baseDir + File.separator + fileBean.getUuid() + File.separator + fileBean.getFileName();
		File file = new File(filePath);
		File parent = new File(file.getParent());
		if (!parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
			this.rfile = new RandomAccessFile(filePath, "rw");
			this.rfile.setLength(fileBean.getFileSize());
			this.rfile.close();
		}
		this.destFile = file;

	}

	@Override
	public FilePackageBean call() throws Exception {
		long offset = FilePackageBean.partSize * fileBean.getIndex();
		try {
			this.rfile = new RandomAccessFile(this.destFile, "rw");
			rfile.seek(offset);
			rfile.write(fileBean.getContent());
			fileBean.setContent(null);
			rfile.close();
			fileBean.toRecive();
		} catch (Exception e) {
			
			fileBean.toResend();
		}
		// 将content 设置成null，用来回收内存
		fileBean.setContent(null);
		return fileBean;
	}
}
