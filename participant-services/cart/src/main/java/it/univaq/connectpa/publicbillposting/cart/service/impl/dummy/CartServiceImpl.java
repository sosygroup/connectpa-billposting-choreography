package it.univaq.connectpa.publicbillposting.cart.service.impl.dummy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.cart.Space;
import it.univaq.connectpa.publicbillposting.cart.repository.CartRepository;
import it.univaq.connectpa.publicbillposting.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public void addSpace(String requestID, Space space) {

		cartRepository.add(requestID, space);
		
	}

	@Override
	public void removeSpace(String requestID, Space space) {

		cartRepository.remove(requestID, space);
		
	}

	@Override
	public List<Space> getSelectedSpaces(String requestID) {

		return cartRepository.findAllByRequestId(requestID);
	}



}
