package it.univaq.connectpa.publicbillposting.posterservice.provider.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;
import it.univaq.connectpa.billposting.posterservice.ConfirmationRequest;
import it.univaq.connectpa.billposting.posterservice.GetBillingRequest;
import it.univaq.connectpa.billposting.posterservice.PaymentData;
import it.univaq.connectpa.billposting.posterservice.PostRequest;
import it.univaq.connectpa.billposting.posterservice.PosterServicePT;
import it.univaq.connectpa.publicbillposting.posterservice.monitor.LoggerCollector;
import it.univaq.connectpa.publicbillposting.posterservice.provider.service.PosterService;

@Component(value = "PosterServicePTImpl")
public class PosterServicePTImpl implements PosterServicePT {

	Logger logger = LoggerFactory.getLogger(PosterServicePTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Autowired
	private PosterService posterService;

	@Override
	public void sendConfirmation(ConfirmationRequest request) {

		logger.info("Poster Service: received confirmationRequest message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectEnd("Send Confirmation", request.getRequestID());
	}

	@Override
	public void sendPostRequest(PostRequest request) {

		logger.info("Poster Service: received postRequest message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectEnd("Send Post Request", request.getRequestID());		
	}

	@Override
	public BillingDetails getBilling(GetBillingRequest request) {

		logger.info("Poster Service: received getBillingRequest message (InstanceId " + request.getRequestID() + ")");

		BillingDetails response = posterService.getBillingDetails(request.getRequestID());
		return response;
	}

	@Override
	public void startPaymentProcess(PaymentData request) {

		logger.info("Poster Service: received paymentData message (InstanceId " + request.getRequestID() + ")");
		loggerCollector.collectEnd("Start Payment Process", request.getRequestID());
	}

}
