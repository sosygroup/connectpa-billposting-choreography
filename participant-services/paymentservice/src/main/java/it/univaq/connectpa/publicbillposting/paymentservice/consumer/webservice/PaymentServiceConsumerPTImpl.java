package it.univaq.connectpa.publicbillposting.paymentservice.consumer.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.billposting.paymentservice.InstanceInfoWithContext;
import it.univaq.connectpa.billposting.paymentservice.PaymentConfirmation;
import it.univaq.connectpa.billposting.paymentservice.PaymentServiceConsumerPT;
import it.univaq.connectpa.publicbillposting.paymentservice.consumer.service.MessageConstructionService;
import it.univaq.connectpa.publicbillposting.paymentservice.monitor.LoggerCollector;

@Component(value = "PaymentServiceConsumerPTImpl")
public class PaymentServiceConsumerPTImpl implements PaymentServiceConsumerPT {

	Logger logger = LoggerFactory.getLogger(PaymentServiceConsumerPTImpl.class);

	@Autowired
	private MessageConstructionService messageConstructionService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public PaymentConfirmation getPaymentConfirmationMessage(InstanceInfoWithContext request) {
		loggerCollector.collectStart("Payment Confirmation", request.getInstanceId());

		logger.info("Payment Service (consumer): request to get paymentConfirmation message(instanceId " + request.getInstanceId() + ")");
		PaymentConfirmation paymentConfirmation = messageConstructionService.getPaymentConfirmationMessage(request.getInstanceId(), request.getContextRef(), request.getServiceContext());
		return paymentConfirmation;
	}


}
