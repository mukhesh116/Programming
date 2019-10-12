package com.keybank.entdemoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class EntDemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				EntDemoServiceApplication.class, args);
	}

}
