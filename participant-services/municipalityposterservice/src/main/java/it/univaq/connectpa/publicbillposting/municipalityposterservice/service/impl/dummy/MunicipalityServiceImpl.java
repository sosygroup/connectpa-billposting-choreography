package it.univaq.connectpa.publicbillposting.municipalityposterservice.service.impl.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.municipalityposterservice.Space;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.model.RequestConfirmation;
import it.univaq.connectpa.publicbillposting.municipalityposterservice.service.MunicipalityService;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {

	@Override
	public List<Space> findPostingSpaces(Date startDate, Date endDate) {

		List <Space> spaces = new ArrayList<Space>();

		Space space = new Space();
		space.setAreaCode("A001");
		space.setAreaName("AreaName");
		space.setMunicipalityCode("123ABC");
		space.setMunicipalityName("Municipality A");
		space.setSpaceCode("S001");
		spaces.add(space);

		return spaces;

	}

	@Override
	public RequestConfirmation confirmRequest(Date startDate, Date endDate, List<Space> spaces) {

		RequestConfirmation confirmation = new RequestConfirmation();
		confirmation.setAmountDue(1.1);
		confirmation.setBillingCode(RandomStringUtils.randomAlphanumeric(10));
		confirmation.setConfirmed(true);
		confirmation.setMunicipalityCode("123ABC");
		confirmation.setMunicipalityName("Municipality A");
		return confirmation;
	}

	@Override
	public void confirmPayment(String billingCode) {
		// Do nothing
	}

}
