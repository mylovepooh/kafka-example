package com.mylovepooh.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylovepooh.kafka.service.KafkaSimpleProducerService;

@RestController
public class KafkaSimpleProducerController {

	@Autowired
	private KafkaSimpleProducerService kafkaSimpleProducerService;

	@PostMapping(value = "/sendMessage")
	public void sendMessage(String message) {
		kafkaSimpleProducerService.sendMessage(message);
	}
	
	@GetMapping(value = "/hello")
	public String hello() {
		return "hello";
	}

}
