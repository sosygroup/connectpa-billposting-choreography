package it.univaq.connectpa.publicbillposting.municipalityposterservice.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.municipalityposterservice.BillingDetails;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.FindRequest;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.FindResponse;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.MunicipalityPosterServicePT;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.PaymentBill;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.PaymentConfirmation;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.Space;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.SpaceConfirmationRequest;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.model.RequestConfirmation;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.monitor.LoggerCollector;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.service.MunicipalityService;


@Component(value = "MunicipalityPosterServicePTImpl")
public class MunicipalityPosterServicePTImpl implements MunicipalityPosterServicePT {

	private static Logger logger = LoggerFactory.getLogger(MunicipalityPosterServicePTImpl.class);

	@Autowired
	private LoggerCollector loggerCollector;

	@Autowired
	private MunicipalityService service;

	@Override
	public BillingDetails confirmPostRequest(SpaceConfirmationRequest request) {

		logger.info("MunicipalityPosterService: received Confirm Post Request request message (InstanceId " + request.getRequestID() + ")");
		RequestConfirmation confirmation = service.confirmRequest(request.getStartDate(), request.getEndDate(), request.getSpaces());

		PaymentBill bill = new PaymentBill();
		bill.setAmountDue(confirmation.getAmountDue());
		bill.setBillingCode(confirmation.getBillingCode());
		bill.setConfirmed(confirmation.isConfirmed());
		bill.setMunicipalityCode(confirmation.getMunicipalityCode());
		bill.setMunicipalityName(confirmation.getMunicipalityName());

		BillingDetails response = new BillingDetails();
		response.setRequestID(request.getRequestID());
		response.getBills().add(bill);

		return response;
	}

	@Override
	public FindResponse findMunicipalityPostingSpaces(FindRequest request) {

		logger.info("MunicipalityPosterService: received Find Municipality Posting Spaces request message (InstanceId " + request.getRequestID() + ")");
		List<Space> spaces = service.findPostingSpaces(request.getStartDate(), request.getStartDate());

		FindResponse response = new FindResponse();
		response.setRequestID(request.getRequestID());
		response.setStartDate(request.getStartDate());
		response.setEndDate(request.getEndDate());
		response.getAvailableSpaces().addAll(spaces);
		
		return response;
	}

	@Override
	public void paymentConfirmation(PaymentConfirmation request) {

		logger.info("Municipality Service: received paymentConfirmation request message (InstanceId " + request.getRequestID() + ")");
		service.confirmPayment(request.getBillingCode());
		loggerCollector.collectEnd("Send Payment Confirmation", request.getRequestID());
	}

}
