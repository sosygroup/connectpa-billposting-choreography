package it.univaq.connectpa.publicbillposting.contexmanager.domain;

public class PaymentVariant {

	public String variantName;

	private PaymentVariantContext context;

	public PaymentVariantContext getContext() {
		return context;
	}

	public void setContext(PaymentVariantContext context) {
		this.context = context;
	}

}
