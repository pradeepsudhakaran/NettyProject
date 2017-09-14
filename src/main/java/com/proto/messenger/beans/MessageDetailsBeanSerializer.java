package com.proto.messenger.beans;


import org.apache.kafka.common.serialization.Serializer;
import com.proto.messenger.beans.MessengerDetailsBean.MessageDetails;

public class MessageDetailsBeanSerializer extends Adapter implements Serializer<MessageDetails> {

	@Override
	public byte[] serialize(String topic, MessageDetails detailsBean) {
		return detailsBean.toByteArray();
	}
}
