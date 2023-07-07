package it.univaq.connectpa.publicbillposting.paymentservice.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.billposting.paymentservice.InstanceInfoWithContext.ServiceContext;
import it.univaq.connectpa.publicbillposting.paymentservice.consumer.service.MessageConstructionService;
import it.univaq.connectpa.billposting.paymentservice.PaymentConfirmation;

@Service
public class MessageConstructionServiceImpl implements MessageConstructionService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Override
	public PaymentConfirmation getPaymentConfirmationMessage(String instanceID, String contextRef, ServiceContext context) {
		PaymentConfirmation paymentConfirmation = new PaymentConfirmation();
		paymentConfirmation.setRequestID(instanceID);
		paymentConfirmation.setMunicipalityName(contextRef);
		paymentConfirmation.setConfirmed(true);
		paymentConfirmation.setBillingCode("0000");
		return paymentConfirmation;
	}

	
}
