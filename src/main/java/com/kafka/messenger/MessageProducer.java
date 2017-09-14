package com.kafka.messenger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.json.JSONObject;

import com.proto.messenger.beans.MessageDetailsBeanSerializer;
import com.proto.messenger.beans.MessengerDetailsBean;
import com.proto.messenger.beans.MessengerDetailsBean.MessageDetails;

public class MessageProducer {
	private final KafkaProducer<Integer, MessageDetails> producer;
    private final String topic;
    
    public  MessageProducer(String topic){
    	Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "OrderProducer");
        producer = new KafkaProducer<>(props, new IntegerSerializer(), new MessageDetailsBeanSerializer());
        this.topic = topic;
    }
    
    public void run(JSONObject requestJSON) {
        int messageNo = 1;
        MessageDetails detailsBean = null;
        try{
            detailsBean = MessengerDetailsBean.MessageDetails.newBuilder()
					.setName(requestJSON.getString("name"))
					.setEmail(requestJSON.getString("e-mail"))		
					.setPhoneNumber(requestJSON.getDouble("phoneNumber")).build();
			
			
			producer.send(new ProducerRecord<Integer, MessageDetails>(topic, detailsBean));
			System.out.println("Sent message: (" + messageNo + ", " + detailsBean.toString() + ")");
            ++messageNo;
        }catch(Exception e ){
        	e.printStackTrace();
        }
        finally{
        	detailsBean = null;
        }
        }
}
