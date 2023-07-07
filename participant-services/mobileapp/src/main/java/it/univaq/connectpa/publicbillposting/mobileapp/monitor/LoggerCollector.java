package it.univaq.connectpa.publicbillposting.mobileapp.monitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class LoggerCollector {

	private static List<EventLog> logs;

	{
		logs = new ArrayList<>();
	}

	public void collectStart(String operation, String instanceId) {
		logs.add(new EventLog(EventType.START, operation, instanceId));
	}

	public void collectEnd(String operation, String instanceId) {
		logs.add(new EventLog(EventType.END, operation, instanceId));
	}

	public List<EventLog> getLogs() {
		return logs;
	}

	public void reset() {
		logs = new ArrayList<>();
	}
}
