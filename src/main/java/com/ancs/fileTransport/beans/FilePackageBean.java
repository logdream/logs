package com.ancs.fileTransport.beans;

import java.util.UUID;

public class FilePackageBean {
	private TYPE type;  //1
	private STATUS status;  //1
	private String uuid; //32
	private Integer total; //4
	private Integer index; //4
	private Integer csize; //4 
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
	
	
	
	public FilePackageBean(Integer total, Integer index, byte[] content) {
		super();
		this.type = TYPE.SEND;
		this.status = STATUS.SUCCESS;
		this.uuid = UUID.randomUUID().toString().replace("-", "");
		this.total = total;
		this.index = index;
		this.content = content;
		if(null!=content)
			this.csize = content.length;
		else
			this.csize = 0;
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
	
}
