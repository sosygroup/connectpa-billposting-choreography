package it.univaq.connectpa.publicbillposting.sia.endpoints.client.impl;

import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.sia.endpoints.cacds.cacdmobileapp.CaCDMobileAppSetInvocationAddressService;
import it.univaq.connectpa.publicbillposting.sia.endpoints.cacds.cacdmobileapp.SetInvocationAddressPT;
import it.univaq.connectpa.publicbillposting.sia.endpoints.cacds.cacdpaymentservice.CaCDPaymentServiceSetInvocationAddressService;
import it.univaq.connectpa.publicbillposting.sia.endpoints.cacds.cacdposterservice.CaCDPosterServiceSetInvocationAddressService;
import it.univaq.connectpa.publicbillposting.sia.endpoints.client.properties.SiaEndpointsClientProperties;

@Component
public class SiaEndpointsClientImpl {

	@Autowired
	private SiaEndpointsClientProperties properties;

	public void setConfigurationAddress() {

		SetInvocationAddressPT caCDMobileAppSIAPT;
		it.univaq.connectpa.publicbillposting.sia.endpoints.cacds.cacdpaymentservice.SetInvocationAddressPT caCDPaymentServiceSIAPT;
		it.univaq.connectpa.publicbillposting.sia.endpoints.cacds.cacdposterservice.SetInvocationAddressPT caCDPosterServiceSIAPT;

		// caCDMobileApp
		CaCDMobileAppSetInvocationAddressService caCDMobileAppSetInvocationAddressService = new CaCDMobileAppSetInvocationAddressService();
		caCDMobileAppSIAPT = caCDMobileAppSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)caCDMobileAppSIAPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cacdmobileapp.sia"));

		caCDMobileAppSIAPT.setInvocationAddress(properties.getValue("names.mobileapp"), properties.getValue("mobileapp"));
		caCDMobileAppSIAPT.setInvocationAddress(properties.getValue("names.cart"), properties.getValue("providers.cart"));
		caCDMobileAppSIAPT.setInvocationAddress(properties.getValue("names.cacdposterservice"), properties.getValue("cds.cacdposterservice"));
		caCDMobileAppSIAPT.setInvocationAddress(properties.getValue("names.contextmanager"), properties.getValue("contextmanager"));

		// caCDPaymentService
		CaCDPaymentServiceSetInvocationAddressService caCDPaymentServiceSetInvocationAddressService = new CaCDPaymentServiceSetInvocationAddressService();
		caCDPaymentServiceSIAPT = caCDPaymentServiceSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)caCDPaymentServiceSIAPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cacdpaymentservice.sia"));

		caCDPaymentServiceSIAPT.setInvocationAddress(properties.getValue("names.paymentservice"), properties.getValue("prosumers.paymentservice.provider"));
		caCDPaymentServiceSIAPT.setInvocationAddress(properties.getValue("names.paymentservice")+"-consumer", properties.getValue("prosumers.paymentservice.consumer"));
		caCDPaymentServiceSIAPT.setInvocationAddress(properties.getValue("names.contextmanager"), properties.getValue("contextmanager"));


		// caCDPosterService
		CaCDPosterServiceSetInvocationAddressService caCDPosterServiceSetInvocationAddressService = new CaCDPosterServiceSetInvocationAddressService();
		caCDPosterServiceSIAPT = caCDPosterServiceSetInvocationAddressService.getSetInvocationAddressServicePort();
		((BindingProvider)caCDPaymentServiceSIAPT).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, properties.getValue("cds.cacdposterservice.sia"));

		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.posterservice"), properties.getValue("prosumers.posterservice.provider"));
		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.posterservice")+"-consumer", properties.getValue("prosumers.posterservice.consumer"));
		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.cart"), properties.getValue("providers.cart"));
		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.cacdmobileapp"), properties.getValue("cds.cacdmobileapp"));
		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.cacdmobileapp")+"-coordination", properties.getValue("cds.cacdmobileapp.coordination"));
		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.cacdpaymentservice"), properties.getValue("cds.cacdpaymentservice"));
		caCDPosterServiceSIAPT.setInvocationAddress(properties.getValue("names.contextmanager"), properties.getValue("contextmanager"));

	}
}
