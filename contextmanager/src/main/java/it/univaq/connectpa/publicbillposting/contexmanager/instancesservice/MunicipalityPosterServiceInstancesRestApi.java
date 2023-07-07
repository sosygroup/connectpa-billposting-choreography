package it.univaq.connectpa.publicbillposting.contexmanager.instancesservice;

import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.contexmanager.domain.MunicipalityPosterServiceInstance;
import it.univaq.connectpa.publicbillposting.contexmanager.service.ContextManagerService;

@Component(value = "municipalityPosterServiceInstancesRestApi")
public class MunicipalityPosterServiceInstancesRestApi {

	@Autowired
	private ContextManagerService service;

	@POST
	public void addMunicipalityInstance(MunicipalityPosterServiceInstance instance) {

		service.setMuniticpalityPosterServiceInstance(instance);
	}
}
