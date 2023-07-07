package it.univaq.connectpa.publicbillposting.contexmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.univaq.connectpa.publicbillposting.contexmanager.domain.MunicipalityPosterServiceInstance;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.PaymentVariant;

@Service
public class SelectionFunctions {

	public List<MunicipalityPosterServiceInstance> selectMunicipalities(
			double userLatitude, double userLongitude, double searchingRadius, List<MunicipalityPosterServiceInstance> instances) {

		return instances.stream().filter(instance -> 
			geographicDistance(instance.getContext().referenceLatitude, instance.getContext().referenceLongitude, userLatitude, userLongitude) <= searchingRadius
		).collect(Collectors.toList());
	}

	public PaymentVariant selectPaymentVariant(boolean paymentAvailability, List<PaymentVariant> variants) {

		return variants.stream().filter(
				variant -> variant.getContext().paymentServiceRequired == paymentAvailability
			).findFirst().get();
	}

	private double geographicDistance(double lat1, double lon1, double lat2, double lon2) {
	    final double EARTH_RADIUS = 6371.0;

	    double lat1Rad = Math.toRadians(lat1);
	    double lon1Rad = Math.toRadians(lon1);
	    double lat2Rad = Math.toRadians(lat2);
	    double lon2Rad = Math.toRadians(lon2);

	    double latDiff = lat2Rad - lat1Rad;
	    double lonDiff = lon2Rad - lon1Rad;

	    // Haversine formula
	    double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
	               Math.cos(lat1Rad) * Math.cos(lat2Rad) *
	               Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = EARTH_RADIUS * c;

	    return distance;
	}

}
