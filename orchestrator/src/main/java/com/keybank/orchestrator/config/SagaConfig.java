package com.keybank.orchestrator.config;


import com.keybank.orchestrator.sagas.SagaManager;
import org.axonframework.boot.autoconfig.KafkaAutoConfiguration;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.saga.repository.SagaStore;
import org.axonframework.eventhandling.saga.repository.inmemory.InMemorySagaStore;
import org.axonframework.kafka.eventhandling.consumer.KafkaMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter (KafkaAutoConfiguration.class)
public class SagaConfig {

	@Bean
	public SagaStore mySagaStore() {
		return new InMemorySagaStore(); // JdbcSagaStore, InMemorySagaStore, //
										// JpaSagaStore and MongoSagaStore.
	}

	@Bean
	public SagaConfiguration< SagaManager > mySagaConfiguration( @Autowired KafkaMessageSource kafkaMessageSource) {
		return SagaConfiguration.trackingSagaManager(SagaManager.class, "MyProcessor",
				configuration -> kafkaMessageSource);
	}
}
