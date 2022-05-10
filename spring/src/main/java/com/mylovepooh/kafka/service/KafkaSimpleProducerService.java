package com.mylovepooh.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSimpleProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		System.out.println("send message : " + message);
		this.kafkaTemplate.send("test", message);
	}

}
