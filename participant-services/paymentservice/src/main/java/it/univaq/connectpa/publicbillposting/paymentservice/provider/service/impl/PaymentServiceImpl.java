package it.univaq.connectpa.publicbillposting.paymentservice.provider.service.impl;

import org.springframework.stereotype.Service;

import it.univaq.connectpa.billposting.paymentservice.Receipt;
import it.univaq.connectpa.publicbillposting.paymentservice.provider.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public Receipt pay(String requestID, String billingCode, Double amountDue) {

		Receipt receipt = new Receipt();
		receipt.setRequestID(requestID);
		receipt.setBillingCode(billingCode);
		receipt.setAmountPaid(amountDue);
		receipt.setTransactionCode("00000");

		return receipt;
	}

}