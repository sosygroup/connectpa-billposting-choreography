package it.univaq.connectpa.publicbillposting.contexmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.contexmanager.client.InstanceDescription;
import it.univaq.connectpa.publicbillposting.contexmanager.client.ServiceRegistryClient;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.ChoreographyContext;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.MunicipalityPosterServiceInstance;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.PaymentVariant;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.SystemContext;
import it.univaq.connectpa.publicbillposting.contextmanager.PostRequest;

@Service
public class ContextManagerService {

	@Autowired
	ServiceRegistryClient serviceRegistryClient;

	private static Map<String, ChoreographyContext> choreographyContexts;
	private static List<MunicipalityPosterServiceInstance> municipalityPosterServiceInstances;
	private static List<PaymentVariant> paymentVariants;

	static {
		choreographyContexts = new HashMap<String, ChoreographyContext>();
		municipalityPosterServiceInstances = new ArrayList<MunicipalityPosterServiceInstance>();
		paymentVariants = new ArrayList<PaymentVariant>();
	}

	public void setMuniticpalityPosterServiceInstance(MunicipalityPosterServiceInstance instance) {

		if (instance.getContext()!=null) {
			Optional<MunicipalityPosterServiceInstance> oldInstance = municipalityPosterServiceInstances.stream().filter(e -> e.instanceName.equals(instance.instanceName)).findFirst();

			if (oldInstance.isPresent()) {
				oldInstance.get().setContext(instance.getContext());
			} else {
				municipalityPosterServiceInstances.add(instance);
			}
		}
	}

	public ChoreographyContext getMunicipalityPosterServiceInstanceChoreographyContext(String instanceId) {

		ChoreographyContext context = choreographyContexts.get(instanceId);
		if (context == null) {
			context = new ChoreographyContext();

			context.setSystemContext(new SystemContext());
			context.setMunicipalityPosterServiceEntityContexts(municipalityPosterServiceInstances.stream().map(e -> {return e.getContext();}).collect(Collectors.toList()));
			context.setPaymentVariantContexts(paymentVariants.stream().map(e -> {return e.getContext();}).collect(Collectors.toList()));

			choreographyContexts.put(instanceId, context);
		}
		return context;
	}

	public void setLocationLatitude(String instanceId, PostRequest postRequest) {
		// messageName: postRequest
		// queryString: /locationCoordinates/latitude

		SystemContext context = this.getMunicipalityPosterServiceInstanceChoreographyContext(instanceId).getSystemContext();
		context.userLatitude = postRequest.getLocationCoordinates().getLatitude();
	}

	public void setLocationLongitude(String instanceId, PostRequest postRequest) {
		// messageName: postRequest
		// queryString: /locationCoordinates/latitude

		SystemContext context = this.getMunicipalityPosterServiceInstanceChoreographyContext(instanceId).getSystemContext();
		context.userLongitude = postRequest.getLocationCoordinates().getLongitude();
	}

	public void setSearchingRadius(String instanceId, PostRequest postRequest) {
		// messageName: postRequest
		// queryString: /searchRadius

		SystemContext context = this.getMunicipalityPosterServiceInstanceChoreographyContext(instanceId).getSystemContext();
		context.searchingRadius = postRequest.getSearchRadius();
	}

	public List<MunicipalityPosterServiceInstance> getMunicipalityPosterServiceInstances() {

		return municipalityPosterServiceInstances; 
	}

	public List<PaymentVariant> getPaymentVariants() {

		return paymentVariants;
	}

	public Map<String, String> getServiceInstanceUrl(String service, List<String> instanceNames) {
		List<InstanceDescription> instanceDescriptions = serviceRegistryClient.getServiceInstanceDescription(service, instanceNames);

		Map<String, String> result = new HashMap<String, String>();

		instanceDescriptions.forEach(description -> {
			result.put(description.getInstanceName(), description.getEndpoint());
		});

		return result;
	}

}
