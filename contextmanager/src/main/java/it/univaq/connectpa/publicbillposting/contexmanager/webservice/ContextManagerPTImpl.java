package it.univaq.connectpa.publicbillposting.contexmanager.webservice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.contexmanager.domain.MunicipalityPosterServiceContext;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.MunicipalityPosterServiceInstance;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.PaymentVariant;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.SystemContext;
import it.univaq.connectpa.publicbillposting.contexmanager.monitor.LoggerCollector;
import it.univaq.connectpa.publicbillposting.contexmanager.service.AcquisitionFunctions;
import it.univaq.connectpa.publicbillposting.contexmanager.service.ContextManagerService;
import it.univaq.connectpa.publicbillposting.contexmanager.service.SelectionFunctions;
import it.univaq.connectpa.publicbillposting.contextmanager.AcquirePostRequestMessageRequest;
import it.univaq.connectpa.publicbillposting.contextmanager.ContextManagerPT;
import it.univaq.connectpa.publicbillposting.contextmanager.InstanceDescriptor;
import it.univaq.connectpa.publicbillposting.contextmanager.MunicipalityPosterServiceSelectionResponse;
import it.univaq.connectpa.publicbillposting.contextmanager.ServiceInstance;
import it.univaq.connectpa.publicbillposting.contextmanager.ServiceInstance.ServiceContext;

@Component(value = "ContextManagerPTImpl")
public class ContextManagerPTImpl implements ContextManagerPT {

	private final Logger logger = LoggerFactory.getLogger(ContextManagerPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Autowired
	private ContextManagerService contextManager;

	@Autowired
	private SelectionFunctions selectionFunctions;

	@Autowired
	private AcquisitionFunctions acquisitionFunctions;

	@Override
	public void acquirePostRequestMessage(AcquirePostRequestMessageRequest request) {

		contextManager.setLocationLatitude(request.getInstanceId(), request.getMessage());
		contextManager.setLocationLongitude(request.getInstanceId(), request.getMessage());
		contextManager.setSearchingRadius(request.getInstanceId(), request.getMessage());
	}

	@Override
	public MunicipalityPosterServiceSelectionResponse selectMunicipalityPosterService(InstanceDescriptor request) {

		loggerCollector.collectStart("MunicipalityPosterServiceSelection", request.getInstanceId());
		logger.info("MunicipalityPosterService selection Start time for instance " + request.getInstanceId() + ": " + System.currentTimeMillis());

		SystemContext context = contextManager.getMunicipalityPosterServiceInstanceChoreographyContext(request.getInstanceId()).getSystemContext();

		List<MunicipalityPosterServiceInstance> instances = selectionFunctions.selectMunicipalities(context.userLatitude, context.userLongitude, context.searchingRadius, contextManager.getMunicipalityPosterServiceInstances());

		Map<String, String> InstancesUrlMap = contextManager.getServiceInstanceUrl(MunicipalityPosterServiceContext.ENTITY_CLASS_NAME, instances.stream().map(e -> e.instanceName).collect(Collectors.toList()));

		MunicipalityPosterServiceSelectionResponse response = new MunicipalityPosterServiceSelectionResponse();
		instances.forEach(instance-> {
			ServiceInstance selected = new ServiceInstance();
			selected.setName(instance.instanceName);
			selected.setAddress(InstancesUrlMap.get(instance.instanceName));

			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setReferenceLatitude(instance.getContext().getReferenceLatitude());
			serviceContext.setReferenceLongitude(instance.getContext().getReferenceLongitude());
			selected.setServiceContext(serviceContext);

			response.getSelected().add(selected);
		});

		loggerCollector.collectEnd("MunicipalityPosterServiceSelection", request.getInstanceId());
		logger.info("MunicipalityPosterService selection End time for instance " + request.getInstanceId() + ": " + System.currentTimeMillis());

		return response;

	}

	@Override
	public String selectPaymentVariant(InstanceDescriptor request) {

		loggerCollector.collectStart("PaymentVariantSelection", request.getInstanceId());
		logger.info("PaymentVariant selection Start time for instance " + request.getInstanceId() + ": " + System.currentTimeMillis());

		SystemContext context = contextManager.getMunicipalityPosterServiceInstanceChoreographyContext(request.getInstanceId()).getSystemContext();

		context.paymentAvailability = acquisitionFunctions.getPaymentAvailability();

		PaymentVariant variant = selectionFunctions.selectPaymentVariant(context.paymentAvailability, contextManager.getPaymentVariants()); 

		loggerCollector.collectEnd("PaymentVariantSelection", request.getInstanceId());
		logger.info("PaymentVariant selection End time for instance " + request.getInstanceId() + ": " + System.currentTimeMillis());

		return variant.variantName;
	}

}
