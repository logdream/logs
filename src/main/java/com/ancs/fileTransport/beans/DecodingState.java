package com.ancs.fileTransport.beans;
/**
 * bt.writeByte(msg.getType().getByteValue());
		bt.writeByte(msg.getStatus().getByteValue());
		bt.writeInt(msg.getTotal());
		bt.writeInt(msg.getIndex());
		bt.writeInt(msg.getCsize());
		bt.writeBytes(msg.getUuid().getBytes());
		bt.writeBytes(msg.getContent());
		
 * @author surface
 *
 */
public enum DecodingState {
    TYPE,
    STATUS,
    TOTAL,
    INDEX,
    UUID,
    CSIZE,
    CONTENT
}