package it.univaq.connectpa.publicbillposting.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.business.repository.EventRepository;
import it.univaq.connectpa.publicbillposting.common.model.Instance;
import it.univaq.connectpa.publicbillposting.common.model.InstanceDetail;
import it.univaq.connectpa.publicbillposting.common.model.Task;
import it.univaq.connectpa.publicbillposting.domain.Event;
import it.univaq.connectpa.publicbillposting.domain.EventType;
import it.univaq.connectpa.publicbillposting.properties.TaskProperties;

@Service
public class InstanceService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	TaskProperties taskOrderProperties;

	@Value("${monitor.operation.startoperation}")
	private String startOperation;

	public List<Instance> getInstances() {

		Map<String, Instance> instanceMap = new HashMap<>();

		List<Event> firstInstanceEvents = eventRepository.findByOperationAndEventType(startOperation, EventType.START);
		List<Event> lastInstanceEvents = eventRepository.findMaxTimestampInstanceEvents();

		firstInstanceEvents.forEach(event -> {
			Instance instance = new Instance();
			instance.setId(event.getInstanceId());
			instance.setStartTime(new Date(event.getTimestamp()));
			instance.setTotalDuration(0 - event.getTimestamp());
			instanceMap.put(event.getInstanceId(), instance);
		});

		lastInstanceEvents.forEach(event -> {
			Instance instance = instanceMap.get(event.getInstanceId());

			if (instance == null) {
				instance = new Instance();
				instance.setId(event.getInstanceId());
				instance.setTotalDuration(0L);
				instanceMap.put(event.getInstanceId(), instance);
			}
			instance.setTotalDuration(instance.getTotalDuration() + event.getTimestamp());
		});

		List<Instance> result = new ArrayList<>(instanceMap.values());
		Collections.sort(result);
		return result;
	}

	public InstanceDetail getInstanceDetails(String instanceId) {

		List<Event> events = eventRepository.findByInstanceId(instanceId);
		Long firstTimestamp = eventRepository.findFirstByInstanceIdOrderByTimestampAsc(instanceId).getTimestamp();

		Map<String, Task> taskMap = new HashMap<String, Task>();

		events.forEach(event -> {
			if (!taskMap.containsKey(event.getOperation())) {
				Task task = new Task();
				task.setName(event.getOperation());
				taskMap.put(event.getOperation(), task);
			}

			switch (event.getEventType()) {
				case START:
					taskMap.get(event.getOperation()).setRelativeStartTime(event.getTimestamp() - firstTimestamp);
					break;
				case END:
					taskMap.get(event.getOperation()).setRelativeEndTime(event.getTimestamp() - firstTimestamp);
					break;
			}

		});

		InstanceDetail instanceDetail = new InstanceDetail();
		instanceDetail.setInstanceId(instanceId);

		taskOrderProperties.getName().forEach(taskName -> {
			Task task = taskMap.get(taskName);

			if ((task == null && !taskOrderProperties.getFilter().contains(taskName))
					|| (task != null && (task.getRelativeStartTime() == null || task.getRelativeEndTime() == null))) {
				instanceDetail.getMissingData().add(taskName);
			} else if (task != null) {
				instanceDetail.getTasks().add(task);
			}
		});

		return instanceDetail;
	}
}
