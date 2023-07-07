package it.univaq.connectpa.publicbillposting.cart.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import it.univaq.connectpa.publicbillposting.cart.Space;

@Repository
public class CartRepository {

	private static Map<String, Set<Space>> repository;

	{
		repository = new HashMap<String, Set<Space>>();
	}

	public List<Space> findAllByRequestId(String requestId){
		return repository.get(requestId)!=null ? new ArrayList<Space>(repository.get(requestId)) : Collections.emptyList();
	}

	public void add(String requestId, Space space) {
		Set<Space> spaces = repository.get(requestId);

		if (spaces==null) {
			spaces = new LinkedHashSet<Space>();
			repository.put(requestId, spaces);
		}

		spaces.add(space);
	}

	public void remove(String requestId, Space space) {
		Set<Space> spaces = repository.get(requestId);

		if (spaces!=null) {
			spaces.remove(space);
		}
	}
}
