package com.ancs.fileTransport.beans;

public enum STATUS implements ByteValue{
	SUCCESS,FAIL;

	public byte getByteValue() {
		// TODO Auto-generated method stub
		if(this ==STATUS.SUCCESS)
		return 0;
		else
			return 1;
	}
	public static STATUS fromByte(byte b){
		if(b==0)
			return SUCCESS;
		else
			return FAIL;
	}
}
