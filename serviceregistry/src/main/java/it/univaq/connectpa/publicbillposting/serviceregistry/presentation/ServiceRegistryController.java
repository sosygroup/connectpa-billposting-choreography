package it.univaq.connectpa.publicbillposting.serviceregistry.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.univaq.connectpa.publicbillposting.serviceregistry.business.ServiceRegistryService;
import it.univaq.connectpa.publicbillposting.serviceregistry.common.model.InstanceDescription;

@RestController("")
public class ServiceRegistryController {

	@Autowired
	private ServiceRegistryService service;

    @GetMapping("/{serviceName}")
    public List<InstanceDescription> getInstanceDescription(@PathVariable("serviceName") String serviceName, @RequestBody List<String> instanceNames) {

    	return service.getServiceInstancesDescription(serviceName, instanceNames);
    }

    @GetMapping("/{serviceName}/{instanceName}")
    public InstanceDescription getInstanceDescription(@PathVariable("serviceName") String serviceName, @PathVariable("instanceName") String instanceName) {

    	return service.getServiceInstanceDescription(serviceName, instanceName);
    }

    @PostMapping
    public void addInstance(@RequestBody InstanceDescription instanceDescription) {

    	service.addServiceInstance(instanceDescription.getServiceName(), instanceDescription.getInstanceName(), instanceDescription.getEndpoint());
    }

    @DeleteMapping("/{serviceName}/{instanceName}")
    public void deleteInstance(@PathVariable("serviceName") String serviceName, @PathVariable("instanceName") String instanceName) {

    	service.removeServiceInstance(serviceName, instanceName);
    }
}
