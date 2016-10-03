package com.ghag.rnd.kafka.SampleKafkaClients;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaSender {
	
	public static final String KAFKA_BROKER_URL="54.210.125.52:9092";
	

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", KAFKA_BROKER_URL);
		props.put("acks", "all");
		props.put("retries", 0);
		//props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		System.out.println("before new producer");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		System.out.println("after new producer");
		for (int i = 0; i < 5; i++){
			System.out.println("before send"+i);
			producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
			System.out.println("after send"+i);
		}

		producer.close();

	}

}
