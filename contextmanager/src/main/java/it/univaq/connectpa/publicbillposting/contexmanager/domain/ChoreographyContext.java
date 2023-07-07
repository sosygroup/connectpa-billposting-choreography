package it.univaq.connectpa.publicbillposting.contexmanager.domain;

import java.util.List;

public class ChoreographyContext {

	private SystemContext systemContext;

	private List<MunicipalityPosterServiceContext> municipalityPosterServiceEntityContexts;

	private List<PaymentVariantContext> paymentVariantContexts;

	public SystemContext getSystemContext() {
		return systemContext;
	}

	public void setSystemContext(SystemContext systemContext) {
		this.systemContext = systemContext;
	}

	public List<MunicipalityPosterServiceContext> getMunicipalityPosterServiceEntityContexts() {
		return municipalityPosterServiceEntityContexts;
	}

	public void setMunicipalityPosterServiceEntityContexts(
			List<MunicipalityPosterServiceContext> municipalityPosterServiceEntityContexts) {
		this.municipalityPosterServiceEntityContexts = municipalityPosterServiceEntityContexts;
	}

	public List<PaymentVariantContext> getPaymentVariantContexts() {
		return paymentVariantContexts;
	}

	public void setPaymentVariantContexts(List<PaymentVariantContext> paymentVariantContexts) {
		this.paymentVariantContexts = paymentVariantContexts;
	}
	
}
