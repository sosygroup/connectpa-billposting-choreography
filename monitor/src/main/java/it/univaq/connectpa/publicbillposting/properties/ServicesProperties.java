package it.univaq.connectpa.publicbillposting.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "monitor.services")
public class ServicesProperties {

	private String endpoint;

	private List<String> baseUrl;
}
