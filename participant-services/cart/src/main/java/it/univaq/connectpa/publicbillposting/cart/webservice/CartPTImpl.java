package it.univaq.connectpa.publicbillposting.cart.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.univaq.connectpa.publicbillposting.cart.CartID;
import it.univaq.connectpa.publicbillposting.cart.CartPT;
import it.univaq.connectpa.publicbillposting.cart.Space;
import it.univaq.connectpa.publicbillposting.cart.SpaceList;
import it.univaq.connectpa.publicbillposting.cart.SpaceRequest;
import it.univaq.connectpa.publicbillposting.cart.monitor.LoggerCollector;
import it.univaq.connectpa.publicbillposting.cart.service.CartService;


@Component(value = "CartPTImpl")
public class CartPTImpl implements CartPT {

	private static Logger logger = LoggerFactory.getLogger(CartPTImpl.class);

	@Autowired
	private CartService cartService;

	@Autowired
	private LoggerCollector loggerCollector;

	@Override
	public void addSpace(SpaceRequest request) {
		logger.info("Cart: received addSpace request message (InstanceId " + request.getRequestID() + ")");
		cartService.addSpace(request.getRequestID(), request.getSpace());
		loggerCollector.collectEnd("Add Space", request.getRequestID());
		
	}

	@Override
	public void removeSpace(SpaceRequest request) {
		logger.info("Cart: received removeSpace request message (InstanceId " + request.getRequestID() + ")");
		cartService.removeSpace(request.getRequestID(), request.getSpace());
		loggerCollector.collectEnd("Remove Space", request.getRequestID());
		
	}

	@Override
	public SpaceList getSelectedSpaces(CartID request) {
		logger.info("Cart: received getSelectedSpaces request message (InstanceId " + request.getId() + ")");
		List<Space> selectedSpaces = cartService.getSelectedSpaces(request.getId());
		SpaceList response = new SpaceList();
		response.setRequestID(request.getId());
		response.getSpaces().addAll(selectedSpaces);
		return response;
	}

}
