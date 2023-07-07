package it.univaq.connectpa.publicbillposting.mobileapp.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.mobileapp.MobileAppPT;
import it.univaq.connectpa.publicbillposting.mobileapp.PaymentInvoice;
import it.univaq.connectpa.publicbillposting.mobileapp.PostResponse;
import it.univaq.connectpa.publicbillposting.mobileapp.Receipt;
import it.univaq.connectpa.publicbillposting.mobileapp.client.MobileAppClient;
import it.univaq.connectpa.publicbillposting.mobileapp.monitor.LoggerCollector;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.BillingDetails;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.ConfirmationRequest;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.GetBillingRequest;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.PaymentData;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.Space;
import it.univaq.connectpa.publicbillposting.mobileapp.providers.SpaceRequest;

@Component(value = "MobileAppPTImpl")
public class MobileAppPTImpl implements MobileAppPT {

	private static Logger logger = LoggerFactory.getLogger(MobileAppPTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Autowired
	private MobileAppClient client;

	@Override
	public void reply(PostResponse request) {

		logger.info("MobileApp: received Reply request message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectEnd("Reply", request.getRequestID());

		logger.info("MobileApp: waiting 1.5s (InstanceId " + request.getRequestID() + ")");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("MobileApp: sending Add Space request message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectStart("Add Space", request.getRequestID());

		Space space = new Space();
		space.setAreaCode("A001");
		space.setAreaName("AreaName");
		space.setMunicipalityCode("123ABC");
		space.setMunicipalityName("Municipality A");
		space.setSpaceCode("S001");
		
		SpaceRequest spaceRequest = new SpaceRequest();
		spaceRequest.setRequestID(request.getRequestID());
		spaceRequest.setSpace(space);

		client.callAddSpace(spaceRequest);

		logger.info("MobileApp: waiting 1.5s (InstanceId " + request.getRequestID() + ")");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("MobileApp: sending Send Confirmation request message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectStart("Send Confirmation", request.getRequestID());

		ConfirmationRequest confirmationRequest = new ConfirmationRequest();
		confirmationRequest.setRequestID(request.getRequestID());

		client.callSendConfirmation(confirmationRequest);

		logger.info("MobileApp: waiting 1.5s (InstanceId " + request.getRequestID() + ")");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("MobileApp: sending Get Billing request message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectStart("Get Billing", request.getRequestID());

		GetBillingRequest getBillingRequest = new GetBillingRequest();
		getBillingRequest.setRequestID(request.getRequestID());

		BillingDetails billingDetails = client.callGetBilling(getBillingRequest);
		loggerCollector.collectEnd("Get Billing", request.getRequestID());

		logger.info("MobileApp: waiting 1.5s (InstanceId " + request.getRequestID() + ")");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("MobileApp: sending Start Payment Process request message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectStart("Start Payment Process", request.getRequestID());

		PaymentData paymentData = new PaymentData();
		paymentData.setRequestID(request.getRequestID());
		paymentData.setAmountDue(billingDetails.getTotalAmount());
		paymentData.setBillingCode("0000");
		
		client.callStartPaymentProcess(paymentData);
	}

	@Override
	public void replyWithReceipt(Receipt request) {

		logger.info("MobileApp: received Reply With Receipt request message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectEnd("Reply With Receipt", request.getRequestID());
	}

	@Override
	public void replyWithInvoice(PaymentInvoice request) {

		logger.info("MobileApp: received Reply With Invoicerequest message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectEnd("Reply With Invoice", request.getRequestID());
	}

}
