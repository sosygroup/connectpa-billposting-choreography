package it.univaq.connectpa.publicbillposting.paymentservice.provider.service;

import it.univaq.connectpa.billposting.paymentservice.Receipt;

public interface PaymentService {

	public Receipt pay(String requestID, String billingCode, Double amountDue);

}
