package it.univaq.connectpa.publicbillposting.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.univaq.connectpa.publicbillposting.domain.Event;
import it.univaq.connectpa.publicbillposting.domain.EventType;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

	@Query("SELECT COUNT(DISTINCT event.instanceId) " +
			"FROM Event event")
	Long countGroupByInstanceId();

	List<Event> findByOperationAndEventType(String operation, EventType eventType);

	@Query(nativeQuery = true, value = "SELECT event.* FROM Event event " +
			"INNER JOIN (SELECT instanceId, MAX(timestamp) as maxTimestamp " + 
			"FROM Event " + 
			"GROUP BY instanceId) as maxEvents " + 
			"ON maxEvents.instanceId = event.instanceId " + 
			"AND maxEvents.maxTimestamp = event.timestamp")
	List<Event> findMaxTimestampInstanceEvents();

	List<Event> findByInstanceId(String instanceId);

	Event findFirstByInstanceIdOrderByTimestampAsc(String instanceId);
}
