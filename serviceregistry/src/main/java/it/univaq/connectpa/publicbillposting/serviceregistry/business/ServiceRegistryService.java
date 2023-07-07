package it.univaq.connectpa.publicbillposting.serviceregistry.business;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.serviceregistry.business.repository.ServiceInstanceRepository;
import it.univaq.connectpa.publicbillposting.serviceregistry.common.model.InstanceDescription;
import it.univaq.connectpa.publicbillposting.serviceregistry.domain.ServiceInstance;

@Service
public class ServiceRegistryService {

	@Autowired
	private ServiceInstanceRepository repository;

	Logger logger = LoggerFactory.getLogger(ServiceRegistryService.class);

	public InstanceDescription getServiceInstanceDescription(String serviceName, String instanceName) {

		ServiceInstance instance = repository.findByServiceNameAndInstanceName(serviceName, instanceName);

		if (instance !=null) {
			InstanceDescription description = new InstanceDescription();
			description.setServiceName(instance.getInstanceName());
			description.setInstanceName(instance.getInstanceName());
			description.setEndpoint(instance.getBaseURL());
			return description;

		} else {
			return null;
		}
	}

	public List<InstanceDescription> getServiceInstancesDescription(String serviceName) {

		List<ServiceInstance> instances = repository.findByServiceName(serviceName);

		return instances.stream().map(instance -> {
			InstanceDescription description = new InstanceDescription();
			description.setServiceName(instance.getInstanceName());
			description.setInstanceName(instance.getInstanceName());
			description.setEndpoint(instance.getBaseURL());
			return description;
		}).collect(Collectors.toList());
	}

	public List<InstanceDescription> getServiceInstancesDescription(String serviceName, List<String> instanceNames) {

		List<ServiceInstance> instances = repository.findByServiceName(serviceName);

		return instances.stream().map(instance -> {
			InstanceDescription description = new InstanceDescription();
			description.setServiceName(instance.getInstanceName());
			description.setInstanceName(instance.getInstanceName());
			description.setEndpoint(instance.getBaseURL());
			return description;
		}).collect(Collectors.toList());
	}

	public void addServiceInstance(String serviceName, String instanceName, String baseURL) {
		ServiceInstance instance = new ServiceInstance();
		instance.setServiceName(serviceName);
		instance.setInstanceName(instanceName);
		instance.setBaseURL(baseURL);

		repository.save(instance);
	}

	public void removeServiceInstance(String serviceName, String instanceName) {
		repository.deleteByServiceNameAndInstanceName(serviceName, instanceName);
	}
}
