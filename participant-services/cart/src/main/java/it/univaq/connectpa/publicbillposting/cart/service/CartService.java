package it.univaq.connectpa.publicbillposting.cart.service;

import java.util.List;

import it.univaq.connectpa.publicbillposting.cart.Space;

public interface CartService {

	public void addSpace(String requestID, Space space);

	public void removeSpace(String requestID, Space space);

	public List<Space> getSelectedSpaces(String requestID);
}
