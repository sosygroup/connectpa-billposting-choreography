package it.univaq.connectpa.publicbillposting.posterservice.consumer.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univaq.connectpa.publicbillposting.posterservice.consumer.repository.MessageRepository;

@Repository
public class MessageRepositoryDummyImpl implements MessageRepository {

	private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryDummyImpl.class);

	private static Map<String, Map<String, String>> repository;

	static {
		repository = new HashMap<>();
		logger.info("Initializing PosterService repository");
	}


	@Override
	public <T> T get(String messageName, String instanceId, Class<T> t) {

		Map<String, String> instanceMessages = repository.get(instanceId);

		if (instanceMessages != null) {

			String messageJson = instanceMessages.get(messageName);

			if (messageJson != null) {
				ObjectMapper mapper = new ObjectMapper();
				
				try {
					return mapper.readValue(messageJson, t);
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}


	@Override
	public <T> void store(String messageName, String instanceId, T message) {
		ObjectMapper mapper = new ObjectMapper();
		String messageJson;

		Map<String, String> instanceMessages = repository.getOrDefault(instanceId, new HashMap<>());
		
		try {
			messageJson = mapper.writeValueAsString(message);
			instanceMessages.put(messageName, messageJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
