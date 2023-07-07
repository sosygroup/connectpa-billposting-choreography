package it.univaq.connectpa.publicbillposting.posterservice.consumer.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;
import it.univaq.connectpa.billposting.posterservice.CartID;
import it.univaq.connectpa.billposting.posterservice.FindRequest;
import it.univaq.connectpa.billposting.posterservice.FindResponse;
import it.univaq.connectpa.billposting.posterservice.InstanceInfo;
import it.univaq.connectpa.billposting.posterservice.InstanceInfoWithContext;
import it.univaq.connectpa.billposting.posterservice.PaymentData;
import it.univaq.connectpa.billposting.posterservice.PaymentInvoice;
import it.univaq.connectpa.billposting.posterservice.PostResponse;
import it.univaq.connectpa.billposting.posterservice.PosterServiceConsumerPT;
import it.univaq.connectpa.billposting.posterservice.Receipt;
import it.univaq.connectpa.billposting.posterservice.SpaceConfirmationRequest;
import it.univaq.connectpa.billposting.posterservice.SpaceList;
import it.univaq.connectpa.publicbillposting.posterservice.consumer.service.MessageConstructionService;
import it.univaq.connectpa.publicbillposting.posterservice.consumer.service.MessageStoringService;
import it.univaq.connectpa.publicbillposting.posterservice.monitor.LoggerCollector;

@Component(value = "PosterServiceConsumerPTImpl")
public class PosterServiceConsumerPTImpl implements PosterServiceConsumerPT {

	Logger logger = LoggerFactory.getLogger(PosterServiceConsumerPTImpl.class);

	@Autowired
	private MessageConstructionService messageConstructionService;

	@Autowired
	private MessageStoringService messageStoringService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public FindRequest getFindRequestMessage(InstanceInfoWithContext request) {

		loggerCollector.collectStart("Find Municipality Posting Spaces", request.getInstanceId());
		logger.info("PosterService (consumer): request to get findRequest message(instanceId " + request.getInstanceId() + ", contextRef" + request.getContextRef() + ")");
		return messageConstructionService.getFindRequestMessageWithContext(request.getInstanceId(), request.getContextRef());
	}

	@Override
	public void storeFindResponseMessage(String instanceId, String contextRef, FindResponse message) {

		loggerCollector.collectEnd("Find Municipality Posting Spaces", instanceId);
		messageStoringService.storeFindResponseMessage(instanceId, contextRef, message);
	}

	@Override
	public PostResponse getPostResponseMessage(InstanceInfo request) {
		
		loggerCollector.collectStart("Reply", request.getInstanceId());
		logger.info("PosterService (consumer): request to get findRequest message(instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getPostResponseMessage(request.getInstanceId());
	}

	@Override
	public CartID getCartIDMessage(InstanceInfo request) {

		loggerCollector.collectStart("Get Selected Spaces", request.getInstanceId());
		logger.info("PosterService (consumer): request to cartID message(instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getCartIDMessage(request.getInstanceId());
	}


	@Override
	public void storeSpaceListMessage(String instanceId, SpaceList message) {

		loggerCollector.collectEnd("Get Selected Spaces", instanceId);
		messageStoringService.storeSpaceListMessage(instanceId, message);
	}

	@Override
	public SpaceConfirmationRequest getSpaceConfirmationRequestMessage(InstanceInfoWithContext request) {

		loggerCollector.collectStart("Confirm Post Request", request.getInstanceId());
		logger.info("PosterService (consumer): request to get spaceConfirmationRequest message(instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getSpaceConfirmationRequestMessageWithContext(request.getInstanceId(), request.getContextRef());
	}

	@Override
	public void storeBillingDetailsMessage(String instanceId, String contextRef, BillingDetails message) {

		loggerCollector.collectEnd("Confirm Post Request", instanceId);
		messageStoringService.storeBillingDetailsMessage(instanceId, contextRef, message);
	}

	@Override
	public PaymentData getPaymentDataMessage(InstanceInfo request) {

		loggerCollector.collectStart("Send Payment", request.getInstanceId());
		logger.info("PosterService (consumer): request to get paymentData message(instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getPaymentDataMessage(request.getInstanceId());

	}

	@Override
	public void storeReceiptMessage(String instanceId, Receipt message) {

		loggerCollector.collectEnd("Send Payment", instanceId);
		messageStoringService.storeReceiptMessage(instanceId, message);
	}


	@Override
	public Receipt getReceiptMessage(InstanceInfo request) {

		loggerCollector.collectStart("Reply With Receipt", request.getInstanceId());
		logger.info("PosterService (consumer): request to get receipt message(instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getReceiptMessage(request.getInstanceId());
	}

	@Override
	public PaymentInvoice getPaymentInvoiceMessage(InstanceInfo request) {

		loggerCollector.collectStart("Reply With Invoice", request.getInstanceId());
		logger.info("PosterService (consumer): request to get paymentInvoice message(instanceId " + request.getInstanceId() + ")");
		return messageConstructionService.getPaymentInvoiceMessage(request.getInstanceId());
	}

}
