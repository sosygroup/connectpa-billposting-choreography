package it.univaq.connectpa.publicbillposting.posterservice.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.billposting.posterservice.BillingDetails;
import it.univaq.connectpa.billposting.posterservice.FindResponse;
import it.univaq.connectpa.billposting.posterservice.Receipt;
import it.univaq.connectpa.billposting.posterservice.SpaceList;
import it.univaq.connectpa.publicbillposting.posterservice.consumer.repository.MessageRepository;
import it.univaq.connectpa.publicbillposting.posterservice.consumer.service.MessageStoringService;


@Service
public class MessageStoringServiceImpl implements MessageStoringService {

	Logger logger = LoggerFactory.getLogger(MessageConstructionServiceImpl.class);

	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void storeFindResponseMessage(String instanceId, String contextRef, FindResponse message) {

		messageRepository.store("findResponse", instanceId, message);
		
	}

	@Override
	public void storeSpaceListMessage(String instanceId, SpaceList message) {

		messageRepository.store("spaceList", instanceId, message);
	}

	@Override
	public void storeBillingDetailsMessage(String instanceId, String contextRef, BillingDetails message) {

		messageRepository.store("billingDetails", instanceId, message);
	}

	@Override
	public void storeReceiptMessage(String instanceId, Receipt message) {

		messageRepository.store("receipt", instanceId, message);
	}

}
