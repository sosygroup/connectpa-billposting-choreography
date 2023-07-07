package it.univaq.connectpa.publicbillposting.contexmanager.domain;

public class PaymentVariantContext {

	private PaymentVariant paymentVariant;

	public boolean paymentServiceRequired;

	public PaymentVariant getPaymentVariant() {
		return paymentVariant;
	}

	public void setPaymentVariant(PaymentVariant paymentVariant) {
		this.paymentVariant = paymentVariant;
	}
	
}
