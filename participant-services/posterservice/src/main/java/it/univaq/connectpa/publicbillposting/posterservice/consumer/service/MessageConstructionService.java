package it.univaq.connectpa.publicbillposting.posterservice.consumer.service;

import it.univaq.connectpa.billposting.posterservice.CartID;
import it.univaq.connectpa.billposting.posterservice.FindRequest;
import it.univaq.connectpa.billposting.posterservice.PaymentData;
import it.univaq.connectpa.billposting.posterservice.PaymentInvoice;
import it.univaq.connectpa.billposting.posterservice.PostResponse;
import it.univaq.connectpa.billposting.posterservice.Receipt;
import it.univaq.connectpa.billposting.posterservice.SpaceConfirmationRequest;

public interface MessageConstructionService {

	public FindRequest getFindRequestMessageWithContext(String instanceID, String contextRef);

	public PostResponse getPostResponseMessage(String instanceID);

	public CartID getCartIDMessage(String instanceID);

	public SpaceConfirmationRequest getSpaceConfirmationRequestMessageWithContext(String instanceID, String contextRef);

	public PaymentData getPaymentDataMessage(String instanceID);

	public Receipt getReceiptMessage(String intanceID);

	public PaymentInvoice getPaymentInvoiceMessage(String instanceID);


}
