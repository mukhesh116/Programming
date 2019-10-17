package com.keybank.orchestrator;

import org.axonframework.config.Configuration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.eventhandling.consumer.KafkaMessageSource;
import org.axonframework.extensions.kafka.eventhandling.producer.ConfirmationMode;
import org.axonframework.extensions.kafka.eventhandling.producer.DefaultProducerFactory;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.axonframework.messaging.StreamableMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run( Application.class, args);
	}
	@Bean
	public EmbeddedEventStore eventStore() {
		return EmbeddedEventStore.builder().storageEngine(( EventStorageEngine )(new InMemoryEventStorageEngine ())).build();
	}
	@Bean
	public InMemoryTokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	public ProducerFactory producerFactory( KafkaProperties kafkaProperties) {
		return (ProducerFactory) DefaultProducerFactory.builder().configuration(
				kafkaProperties.buildProducerProperties()).producerCacheSize(10000).confirmationMode( ConfirmationMode.WAIT_FOR_ACK).build();
	}

	@Autowired
	public void configureKafkaSourceForProcessingGroup( EventProcessingConfigurer configurer, final KafkaMessageSource kafkaMessageSource) {
		configurer.registerTrackingEventProcessor("kafka-group", ( Function )( new Function() {
			// $FF: synthetic method
			// $FF: bridge method
			public Object apply(Object var1) {
				return this.apply(( Configuration )var1);
			}

			public final StreamableMessageSource apply( Configuration it) {
				return (StreamableMessageSource)kafkaMessageSource;
			}
		}));
	}
}
