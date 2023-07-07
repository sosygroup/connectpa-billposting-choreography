package it.univaq.connectpa.publicbillposting.mobileapp.starter;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.mobileapp.client.MobileAppClient;
import it.univaq.connectpa.publicbillposting.mobileapp.monitor.LoggerCollector;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.Coordinates;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.PostRequest;

@Component(value = "starterRestApi")
@Path("/start")
public class StarterRestApi {

	private static Logger logger = LoggerFactory.getLogger(StarterRestApi.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Autowired
	private MobileAppClient client;

	@GET
	@Path("{instanceId}")
	public void startInstance(@PathParam("instanceId") String instanceId) {

		logger.info("Starting instance " + instanceId);
		loggerCollector.collectStart("Send Post Request", instanceId);

		Coordinates coordinates = new Coordinates();
		coordinates.setLatitude(42.0);
		coordinates.setLongitude(14.0);

		PostRequest postRequest = new PostRequest();
		postRequest.setRequestID(instanceId);
		postRequest.setSearchRadius(20);
		postRequest.setLocationName("L'Aquila");
		postRequest.setStartDate(new Date());
		postRequest.setEndDate(new Date());

		client.callSendPostRequest(postRequest);
	}
}
