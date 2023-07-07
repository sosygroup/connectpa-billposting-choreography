package it.univaq.connectpa.publicbillposting.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("monitor.task")
public class TaskProperties {

	
	List<String> name;

	List<String> filter;
}
