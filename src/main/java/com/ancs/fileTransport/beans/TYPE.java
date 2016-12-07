package com.ancs.fileTransport.beans;

public enum TYPE implements ByteValue {
	SEND((byte) 0), RECIVE((byte) 1), RESEND((byte) 2),
	ERROR((byte)9);

	private byte typeByte;

	public byte getTypeByte() {
		return typeByte;
	}

	public void setTypeByte(byte typeByte) {
		this.typeByte = typeByte;
	}

	private TYPE(byte typeByte) {
		this.typeByte = typeByte;
	}

	@Override
	public byte getByteValue() {
		// TODO Auto-generated method stub
		return typeByte;
	}

	public static TYPE fromByte(byte b){
		switch (b) {
		case 0:
			return TYPE.SEND;
		case 1:
			return TYPE.RECIVE;
		case 2:
			return TYPE.RESEND;
		default:
			return TYPE.ERROR;
		}
	}
}
