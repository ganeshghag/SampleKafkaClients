package com.ghag.rnd.kafka.SampleKafkaClients;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaReceiver {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", KafkaSender.KAFKA_BROKER_URL);
		props.put("group.id", "test");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		System.out.println("before new consumer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		System.out.println("after new consumer");
		
		consumer.subscribe(Arrays.asList("test"));
		System.out.println("after subscribe");
		
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			System.out.println("after poll");
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
		}

	}

}
