package com.dataaggregator.dataaggregator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides the configuration properties for the Kafka messaging
 * system.
 * 
 * It uses the @Value annotation to inject properties from the application
 * configuration file.
 * 
 * These properties are used to configure the Kafka consumer
 * factories.
 */
@Configuration
public class KafkaConfig {

	@Value("${producerApplicationID}")
	public String producerApplicationID;

	@Value("${spring.kafka.bootstrap-servers}")
	public String bootstrapServers;

	@Value("${group-id}")
	public String groupId;
}
