package it.univaq.connectpa.publicbillposting.posterservice.consumer.repository;

public interface MessageRepository {

	public <T> T get(String messageName, String instanceId, Class<T> t);

	public <T> void store(String messageName, String instanceId, T object);
}
