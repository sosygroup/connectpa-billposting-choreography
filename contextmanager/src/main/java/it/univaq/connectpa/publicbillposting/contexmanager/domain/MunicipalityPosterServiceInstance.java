package it.univaq.connectpa.publicbillposting.contexmanager.domain;

public class MunicipalityPosterServiceInstance {

	public String instanceName;

	private MunicipalityPosterServiceContext context;

	public MunicipalityPosterServiceContext getContext() {
		return context;
	}

	public void setContext(MunicipalityPosterServiceContext context) {
		this.context = context;
	}

}
