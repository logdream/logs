package com.ancs.fileTransport.coder;

import java.util.List;

import com.ancs.fileTransport.beans.DecodingState;
import com.ancs.fileTransport.beans.FilePackageBean;
import com.ancs.fileTransport.beans.STATUS;
import com.ancs.fileTransport.beans.TYPE;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class DecoderBean extends ReplayingDecoder<DecodingState>{

	private FilePackageBean bean;
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(333111111);
		switch(state()){
		case TYPE:
			if(null==bean)
				bean = new FilePackageBean();
			bean.setType(TYPE.formByte(in.readByte()));
			checkpoint(DecodingState.STATUS);
		case STATUS:
			bean.setStatus(STATUS.fromByte(in.readByte()));
			checkpoint(DecodingState.TOTAL);
		case TOTAL:
			bean.setTotal(in.readInt());
			checkpoint(DecodingState.INDEX);
		case INDEX:
			bean.setIndex(in.readInt());
			checkpoint(DecodingState.UUID);
		case UUID:
			bean.setUuid(in.readBytes(32).array()
					.toString());
			checkpoint(DecodingState.CSIZE);
		case CSIZE:
			bean.setCsize(in.readInt());
			checkpoint(DecodingState.CONTENT);
		case CONTENT:
			bean.setContent(in.readBytes(bean.getCsize()).array());
			out.add(bean);
			checkpoint(DecodingState.TYPE);
			break;
		default:
			throw new Error("wtf");			
			
		}
			
	}

	

}
