package com.proto.messenger.beans;

import java.util.Map;

import com.proto.messenger.beans.MessengerDetailsBean.MessageDetails;

public class Adapter {
	public void close() {}
    public void configure(Map<String,?> configs, boolean isKey) {}
	public byte[] serialize(String topic, MessageDetails detailsBean) {
		// TODO Auto-generated method stub
		return null;
	}
	public MessageDetails deserialize(String topic, byte[] detailsArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
