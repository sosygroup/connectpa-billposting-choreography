package it.univaq.connectpa.publicbillposting.posterservice.provider.service.impl;

import org.springframework.stereotype.Service;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;
import it.univaq.connectpa.publicbillposting.posterservice.provider.service.PosterService;

@Service
public class PostererviceImpl implements PosterService {

	@Override
	public BillingDetails getBillingDetails(String requestID) {

		BillingDetails billingDetails = new BillingDetails();
		billingDetails.setRequestID(requestID);
		billingDetails.setTotalAmount(0d);
		return billingDetails;
	}

}
