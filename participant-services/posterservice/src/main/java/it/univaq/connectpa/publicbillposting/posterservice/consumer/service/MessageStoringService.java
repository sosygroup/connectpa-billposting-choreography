package it.univaq.connectpa.publicbillposting.posterservice.consumer.service;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;
import it.univaq.connectpa.billposting.posterservice.FindResponse;
import it.univaq.connectpa.billposting.posterservice.Receipt;
import it.univaq.connectpa.billposting.posterservice.SpaceList;

public interface MessageStoringService {

	public void storeFindResponseMessage(String instanceId, String contextRef, FindResponse message);

	public void storeSpaceListMessage(String instanceId, SpaceList message);

	public void storeBillingDetailsMessage(String instanceId, String contextRef, BillingDetails message);

	public void storeReceiptMessage(String instanceId, Receipt receipt);
	
}