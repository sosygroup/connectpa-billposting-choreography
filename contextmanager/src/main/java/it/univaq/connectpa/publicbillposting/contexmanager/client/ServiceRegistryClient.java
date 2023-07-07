package it.univaq.connectpa.publicbillposting.contexmanager.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceRegistryClient {

	private static final String registryURL = "http://localhost:9090/serviceregistry/";

	@Autowired
	private RestTemplate restTemplate;

	public List<InstanceDescription> getServiceInstanceDescription(String serviceName, List<String> instanceNames) {
		ResponseEntity<InstanceDescription[]> response = restTemplate.postForEntity(registryURL + serviceName, instanceNames,InstanceDescription[].class);

		InstanceDescription[] instances = response.getBody();

		return Arrays.asList(instances);
	}
}
