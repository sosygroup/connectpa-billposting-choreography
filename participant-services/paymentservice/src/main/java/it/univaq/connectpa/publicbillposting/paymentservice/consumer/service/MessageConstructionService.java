package it.univaq.connectpa.publicbillposting.paymentservice.consumer.service;

import it.univaq.connectpa.billposting.paymentservice.PaymentConfirmation;
import it.univaq.connectpa.billposting.paymentservice.InstanceInfoWithContext.ServiceContext;

public interface MessageConstructionService {

	public PaymentConfirmation getPaymentConfirmationMessage(String instanceID, String contextRef, ServiceContext context);

}
