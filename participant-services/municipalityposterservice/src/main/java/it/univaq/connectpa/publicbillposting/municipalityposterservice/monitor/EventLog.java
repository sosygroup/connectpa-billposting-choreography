package it.univaq.connectpa.publicbillposting.municipalityposterservice.monitor;

public class EventLog {

	private final EventType eventType;

	private final String operation;

	private final String instanceId;

	private final Long timestamp;

	public EventLog(EventType eventType, String operation, String instanceId) {
		this.eventType = eventType;
		this.operation = operation;
		this.instanceId = instanceId;
		this.timestamp = System.currentTimeMillis();
	}

	public EventLog(EventType eventType, String operation, String instanceId, Long timestamp) {
		this.eventType = eventType;
		this.operation = operation;
		this.instanceId = instanceId;
		this.timestamp = timestamp;
	}

	public EventType getEventType() {
		return eventType;
	}

	public String getOperation() {
		return operation;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public Long getTimestamp() {
		return timestamp;
	}
	
}
