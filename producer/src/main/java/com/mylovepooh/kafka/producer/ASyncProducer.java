package com.mylovepooh.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ASyncProducer {
	private static String TOPIC_NAME = "test";
	private static String BOOTSTRAP_SERVERS = "192.168.75.184:9092,192.168.75.185:9092,192.168.75.186:9092";

	public static void main(String[] args) {
		Properties configs = new Properties();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.ACKS_CONFIG, "all");
		configs.put(ProducerConfig.RETRIES_CONFIG, "3");
		configs.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

		KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

		for (int index = 0 ; index < 100 ; index++) {
			String data = "This is record " + index;
			ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, data, data);
			try {
				producer.send(record, (recordMetadata, exception) -> {
					if (exception == null) {
						System.out.println("Record written to offset " + recordMetadata.offset() + ", partition : "
								+ recordMetadata.partition() + " timestamp " + recordMetadata.timestamp());
					} else {
						System.err.println("An error occurred");
						exception.printStackTrace(System.err);
					}
				});
				Thread.sleep(100);
			} catch (Exception exception) {
				System.out.println(exception);
			}

		}
		producer.flush();
		producer.close();
	}

}
