package it.univaq.connectpa.publicbillposting.municipalityposterservice.service;

import java.util.Date;
import java.util.List;

import it.univaq.connectpa.publicbillposting.municipalityposterservice.Space;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.model.RequestConfirmation;

public interface MunicipalityService {

	public List<Space> findPostingSpaces(Date startDate, Date endDate);

	public RequestConfirmation confirmRequest(Date startDate, Date endDate, List<Space> spaces);

	public void confirmPayment(String billingCode);
}
