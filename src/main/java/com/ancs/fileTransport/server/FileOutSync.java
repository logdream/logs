package com.ancs.fileTransport.server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.ancs.fileTransport.beans.FilePackageBean;

public class FileOutSync {

	private FilePackageBean fileBean;
	private RandomAccessFile rfile;
	private File destFile;

	public FileOutSync(FilePackageBean fileBean, String baseDir) throws IOException {
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

	/**
	 * 同步的方式将文件写人文件
	 * 
	 * @return
	 */
	public FilePackageBean writeToFile() {
		long offset = FilePackageBean.partSize * fileBean.getIndex();
		try {
			this.rfile = new RandomAccessFile(this.destFile, "rw");
			rfile.seek(offset);
			rfile.write(fileBean.getContent());
			fileBean.setContent(null);
			rfile.close();
			fileBean.toRecive();
		} catch (Exception e) {
			e.printStackTrace();
			fileBean.toResend();

		} finally {
			try {
				if (null != this.rfile) {
					rfile.close();
				}
			} catch (Exception e) {

			}
		}
		// 将content 设置成null，用来回收内存
		fileBean.setContent(null);
		return fileBean;
	}
}
