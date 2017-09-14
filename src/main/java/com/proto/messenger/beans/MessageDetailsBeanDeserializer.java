package com.proto.messenger.beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.proto.messenger.beans.MessengerDetailsBean.MessageDetails;

public class MessageDetailsBeanDeserializer extends Adapter implements Deserializer<MessageDetails> {

	@Override
	public MessageDetails deserialize(String topic, byte[] detailsArray) {
		MessageDetails message = null;
		try{
		message =  MessageDetails.parseFrom(detailsArray);	
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}finally{
			detailsArray=null;
			topic=null;
		}
		return message;
	}

	
}
