package it.univaq.connectpa.publicbillposting.posterservice.consumer.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;
import it.univaq.connectpa.billposting.posterservice.CartID;
import it.univaq.connectpa.billposting.posterservice.FindRequest;
import it.univaq.connectpa.billposting.posterservice.FindResponse;
import it.univaq.connectpa.billposting.posterservice.PaymentData;
import it.univaq.connectpa.billposting.posterservice.PaymentInvoice;
import it.univaq.connectpa.billposting.posterservice.PostResponse;
import it.univaq.connectpa.billposting.posterservice.Receipt;
import it.univaq.connectpa.billposting.posterservice.Space;
import it.univaq.connectpa.billposting.posterservice.SpaceConfirmationRequest;
import it.univaq.connectpa.publicbillposting.posterservice.consumer.repository.MessageRepository;
import it.univaq.connectpa.publicbillposting.posterservice.consumer.service.MessageConstructionService;

@Service
public class MessageConstructionServiceImpl implements MessageConstructionService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public FindRequest getFindRequestMessageWithContext(String instanceID, String contextRef) {

		FindRequest message = new FindRequest();
		message.setRequestID(instanceID);
		message.setStartDate(new Date());
		message.setEndDate(new Date());
		return message;

	}

	@Override
	public PostResponse getPostResponseMessage(String instanceID) {

		FindResponse findResponse = messageRepository.get("findResponse", instanceID, FindResponse.class);

		PostResponse message =new PostResponse();
		message.setRequestID(instanceID);
		if (findResponse != null) {
			message.getAvailableSpaces().add(findResponse.getAvailableSpaces().isEmpty() ? new Space() : findResponse.getAvailableSpaces().get(0));			
		}
		return message;
	}

	@Override
	public CartID getCartIDMessage(String instanceID) {

		CartID message = new CartID();
		message.setId(instanceID);
		return message;
	}

	@Override
	public SpaceConfirmationRequest getSpaceConfirmationRequestMessageWithContext(String instanceID, String contextRef) {

		SpaceConfirmationRequest message = new SpaceConfirmationRequest();
		message.setRequestID(instanceID);
		return message;
	}

	@Override
	public PaymentData getPaymentDataMessage(String instanceID) {

		PaymentData message = new PaymentData();
		message.setRequestID(instanceID);
		message.setBillingCode(instanceID);

		BillingDetails billingDetails = messageRepository.get("billingDetails", instanceID, BillingDetails.class);
		if (billingDetails != null) {
			message.setAmountDue(billingDetails.getTotalAmount());
		}

		return message;
	}

	@Override
	public Receipt getReceiptMessage(String intanceID) {

		Receipt message = new Receipt();
		message.setRequestID(intanceID);
		message.setAmountPaid(0d);
		message.setBillingCode("AAA");
		message.setTransactionCode("AAA");
		return message;
	}

	@Override
	public PaymentInvoice getPaymentInvoiceMessage(String instanceID) {

		PaymentInvoice message = new PaymentInvoice();
		message.setRequestID(instanceID);
		message.setAmountDue(0d);
		message.setBillingCode("AAA");
		return message;
	}

}
