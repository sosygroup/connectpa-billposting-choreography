package it.univaq.connectpa.publicbillposting.contexmanager.domain;

public class MunicipalityPosterServiceContext {

	public static final String ENTITY_CLASS_NAME = "MunicipalityPosterService";

    /*
     * Entity static attributes
     */
    public Double referenceLatitude;
    public Double referenceLongitude;

    private MunicipalityPosterServiceInstance instance;

	public Double getReferenceLatitude() {
        return this.referenceLatitude;
    }

    public void setReferenceLatitude(Double referenceLatitude) {
        this.referenceLatitude = referenceLatitude;
    }

    public Double getReferenceLongitude() {
        return this.referenceLongitude;
    }

    public void setReferenceLongitude(Double referenceLongitude) {
        this.referenceLongitude = referenceLongitude;
    }

	public MunicipalityPosterServiceInstance getInstance() {
		return instance;
	}

	public void setInstance(MunicipalityPosterServiceInstance instance) {
		this.instance = instance;
	}

}