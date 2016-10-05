package com.ghag.rnd.kafka.SampleKafkaClients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaReceiver {
	
	//public static final String KAFKA_ZOOKEEPER_URL="localhost:2181";

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", KafkaSender.KAFKA_BROKER_URL);
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		//props.put("auto.offset.reset", "earliest");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		System.out.println("before new consumer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		System.out.println("after new consumer");
		
		//default subscribe
//		consumer.subscribe(Arrays.asList("test");
		
//		consumer.subscribe(Arrays.asList("test"), new ConsumerRebalanceListener() {
//			
//			@Override
//			public void onPartitionsRevoked(Collection<TopicPartition> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onPartitionsAssigned(Collection<TopicPartition> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

		//partition based subscribe
		ArrayList<TopicPartition> list = new ArrayList<TopicPartition>();
		list.add(new TopicPartition("test", 0));		
		
		System.out.println("size="+consumer.assignment().size());

		//programmatic "auto.offset.reset", "earliest"
		consumer.seekToBeginning(list);

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(1000);
			System.out.println("after poll count="+records.count());
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
		}

	}

}
