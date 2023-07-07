package it.univaq.connectpa.publicbillposting.paymentservice.provider.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.billposting.paymentservice.PaymentData;
import it.univaq.connectpa.billposting.paymentservice.PaymentServicePT;
import it.univaq.connectpa.billposting.paymentservice.Receipt;
import it.univaq.connectpa.publicbillposting.paymentservice.provider.service.PaymentService;

@Component(value = "PaymentServicePTImpl")
public class PaymentServicePTImpl implements PaymentServicePT {

	Logger logger = LoggerFactory.getLogger(PaymentServicePTImpl.class);

	@Autowired
	private PaymentService service;

	@Override
	public Receipt sendPayment(PaymentData request) {

		Receipt receipt = service.pay(request.getRequestID(), request.getBillingCode(), request.getAmountDue());
		return receipt;
		
	}

}
