package com.k8s.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k8s.model.Customer;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = {"com.k8s.config"})
public class K8sKafkaProducerApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(K8sKafkaProducerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(K8sKafkaProducerApplication.class, args);
	}

	private static final String TOPIC="java_in_use_topic";
	@Autowired
    KafkaTemplate<String, Customer> kafkaTemplate;
	@GetMapping("/get")
	public String hello() {
		return "Hellow World!";
	}
	@GetMapping(value = "/producer")
	public String producer() {
		LOGGER.info("Producer........");

		Customer c=new Customer();
		c.setCity("Berhampur");
		c.setCountry("Ind");
		c.setFirstname("Aranab");
		c.setId(101);
		c.setLastname("Mohanty");
		c.setState("Odisha");
		kafkaTemplate.send(TOPIC, c);
		LOGGER.info("Message sent successfully................");
		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
}
