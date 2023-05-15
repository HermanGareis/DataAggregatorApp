package com.dataaggregator.dataaggregator.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dataaggregator.dataaggregator.entity.ComuneDose;
import com.dataaggregator.dataaggregator.service.DataAggregatorService;

/**
 * 
 * This class is a Spring component responsible for listening to Kafka messages
 * and processing them using a DataAggregatorService. It is annotated
 * with @Component to indicate that it should be automatically detected by
 * Spring's component scanning and registered as a bean in the application
 * context. The @KafkaListener annotation on the listen() method specifies the
 * topic to listen to and the container factory to use for consuming messages.
 */

@Component
public class KafkaListeners {

	@Autowired
	private DataAggregatorService service;

	@KafkaListener(topics = "covidDoses", containerFactory = "comuneDoseListener")
	public void listen(ComuneDose comuneDose) {
		System.out.println("Received '" + comuneDose + "' from the covidDoseTopic.");
		service.addComuneDose(comuneDose);
	}

}
