package com.keybank.ccpdemoservice;


import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class CcpDemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				CcpDemoServiceApplication.class, args);
	}

}
