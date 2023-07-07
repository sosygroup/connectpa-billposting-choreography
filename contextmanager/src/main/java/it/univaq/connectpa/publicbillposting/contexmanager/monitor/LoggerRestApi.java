package it.univaq.connectpa.publicbillposting.contexmanager.monitor;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "loggerRestApi")
@Path("/logs")
public class LoggerRestApi {

	@Autowired
	private LoggerCollector loggerCollector;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventLog> getLogs() {
		List <EventLog> result = loggerCollector.getLogs();
		loggerCollector.reset();
		return result;
	}
}
