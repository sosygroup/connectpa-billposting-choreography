package it.univaq.connectpa.publicbillposting.posterservice.provider.service;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;

public interface PosterService {

	public BillingDetails getBillingDetails(String requestID);
}
