package com.ancs.fileTransport.beans;

public enum TYPE implements ByteValue {
	SEND, RECIVE;

	public byte getByteValue() {
		// TODO Auto-generated method stub
		if (this == TYPE.SEND)
			return 0;
		else
			return 1;
	}

	public static TYPE formByte(byte b) {
		if (b == 0) {
			return SEND;
		} else
			return RECIVE;
	}
}
