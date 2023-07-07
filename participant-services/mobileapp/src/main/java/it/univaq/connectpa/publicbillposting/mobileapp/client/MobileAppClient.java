package it.univaq.connectpa.publicbillposting.mobileapp.client;

import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.mobileapp.providers.BillingDetails;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.CaCDMobileAppPT;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.CaCDMobileAppService;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.ConfirmationRequest;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.GetBillingRequest;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.PaymentData;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.PostRequest;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.SpaceRequest;

@Service
public class MobileAppClient {

	private static final String CACDMOBILEAPP_URL = "http://localhost:8080/ode/processes/caCDMobileApp";

	private CaCDMobileAppPT caCDMobileApp;

	public MobileAppClient() {
		CaCDMobileAppService service = new CaCDMobileAppService();
		caCDMobileApp = service.getCaCDMobileAppPort();
		((BindingProvider)caCDMobileApp).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, CACDMOBILEAPP_URL);
	}

	public void callSendPostRequest(PostRequest request) {
		caCDMobileApp.sendPostRequest(request);
	}

	public void callAddSpace(SpaceRequest request) {
		caCDMobileApp.addSpace(request);
	}

	public void callRemoveSpace(SpaceRequest request) {
		caCDMobileApp.removeSpace(request);
	}

	public void callSendConfirmation(ConfirmationRequest request) {
		caCDMobileApp.sendConfirmation(request);
	}

	public BillingDetails callGetBilling(GetBillingRequest request) {
		return caCDMobileApp.getBilling(request);
	}

	public void callStartPaymentProcess(PaymentData request) {
		caCDMobileApp.startPaymentProcess(request);
	}
}
