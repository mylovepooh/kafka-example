package com.mylovepooh.kafka.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaSimpleConsumerService {

	@KafkaListener(topics = "test", groupId = "test-group")
	public void consume(String message) throws IOException {
		System.out.println("receive message : " + message);
	}

}
