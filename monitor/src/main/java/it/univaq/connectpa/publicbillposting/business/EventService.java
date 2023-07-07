package it.univaq.connectpa.publicbillposting.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.univaq.connectpa.publicbillposting.business.repository.EventRepository;
import it.univaq.connectpa.publicbillposting.common.model.AcquisitionResultType;
import it.univaq.connectpa.publicbillposting.domain.Event;
import it.univaq.connectpa.publicbillposting.properties.ServicesProperties;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ServicesProperties servicesProperties;

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(EventService.class);

	public AcquisitionResultType requireEventsFromServices() {

		List<Event> events = new ArrayList<>();

		servicesProperties.getBaseUrl().parallelStream().forEach(serviceUrl -> {

			try {				
				ResponseEntity<Event[]> response = restTemplate.getForEntity(serviceUrl + servicesProperties.getEndpoint(), Event[].class);
				events.addAll(Arrays.asList(response.getBody()));
			} catch (Exception e) {
				logger.error("Unable to get results from " + serviceUrl + ": exception is " + e.getMessage());
			}
		});

		if (events.isEmpty()) {
			return AcquisitionResultType.EMPTY;
		}

		eventRepository.saveAll(events);
		return AcquisitionResultType.OK;
	}

	public Long countInstances() {
		return eventRepository.countGroupByInstanceId();
	}
}
