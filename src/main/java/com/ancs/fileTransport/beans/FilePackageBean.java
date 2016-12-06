package com.ancs.fileTransport.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class FilePackageBean implements Cloneable {
	private TYPE type; // 1
	private STATUS status; // 1
	private Long fileSize;
	private String uuid; // 32
	private Integer total; // 4
	private Integer index; // 4
	private Integer nameSize;
	private String fileName;

	private Integer csize; // 4
	private byte[] content;

	public FilePackageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilePackageBean(TYPE type, STATUS status, String uuid, Integer total, Integer index, Integer csize,
			byte[] content) {
		super();
		this.type = type;
		this.status = status;
		this.uuid = uuid;
		this.total = total;
		this.index = index;
		this.csize = csize;
		this.content = content;
	}

	public Integer getNameSize() {
		return nameSize;
	}

	public void setNameSize(Integer nameSize) {
		this.nameSize = nameSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FilePackageBean(String fileName, Long fileSize, Integer total, Integer index, byte[] content) {
		super();
		this.type = TYPE.SEND;
		this.status = STATUS.SUCCESS;
		this.fileSize = fileSize;
		this.uuid = UUID.randomUUID().toString().replace("-", "");
		this.total = total;
		this.index = index;
		this.content = content;
		if (null != content)
			this.csize = content.length;
		else
			this.csize = 0;
		this.fileName = fileName;
		this.nameSize = fileName.getBytes().length;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getCsize() {
		return csize;
	}

	public void setCsize(Integer csize) {
		this.csize = csize;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	// 通过文件初始化
	private final static Integer partSize = 1024*1023*30 - 60;// 分块大小；
	private InputStream in = null;

	public FilePackageBean(File file) throws IOException {
		this.fileName = file.getName();
		this.fileSize = file.length();
		this.nameSize = file.getName().getBytes().length;
		this.total = Integer.parseInt((file.length() / partSize + 1) + "");
		this.index = -1;
		this.uuid = UUID.randomUUID().toString().replace("-", "");
		this.csize = 0;
		this.in = new FileInputStream(file);
	}

	public boolean hasNext() {
		return this.index + 1 < this.total;
	}

	public FilePackageBean next() throws IOException, CloneNotSupportedException {
		this.index = this.index + 1;
		Long offset = csize + 1L * this.index * partSize;
		if (this.total == this.index + 1) {
			this.csize = Integer.parseInt((this.fileSize - 1L * (this.index) * partSize) + "");
		} else {
			this.csize = partSize;
		}
		this.content = null;
		this.content = new byte[this.csize];
		System.out.println(offset);
		in.skip(offset);
		in.read(this.content, 0, this.csize);
		if (this.total == this.index + 1)
			close();
		FilePackageBean filePackageBean = (FilePackageBean) this.clone();

		return filePackageBean;

	}

	public void close() {
		if (null != in) {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "FilePackageBean [type=" + type + ", status=" + status + ", fileSize=" + fileSize + ", uuid=" + uuid
				+ ", total=" + total + ", index=" + index + ", nameSize=" + nameSize + ", fileName=" + fileName
				+ ", csize=" + csize + "]";
	}

	public Object clone() {
		FilePackageBean o = null;
		try {
			o = (FilePackageBean) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
