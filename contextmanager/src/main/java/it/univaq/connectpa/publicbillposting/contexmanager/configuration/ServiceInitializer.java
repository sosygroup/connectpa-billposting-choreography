package it.univaq.connectpa.publicbillposting.contexmanager.configuration;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.univaq.connectpa.publicbillposting.contexmanager.domain.PaymentVariant;
import it.univaq.connectpa.publicbillposting.contexmanager.domain.PaymentVariantContext;
import it.univaq.connectpa.publicbillposting.contexmanager.service.ContextManagerService;

@Component
public class ServiceInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ContextManagerService contextManager;

	private static class PaymentVariantJSON {
		public String name;
		public boolean paymentServiceRequired;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("paymentVariants.json");
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			PaymentVariantJSON[] paymentVariants = objectMapper.readValue(inputStream, PaymentVariantJSON[].class);

			for (PaymentVariantJSON addingPaymentVariant : paymentVariants) {
				PaymentVariantContext paymentVariantContext = new PaymentVariantContext();
				PaymentVariant paymentVariant = new PaymentVariant();
				paymentVariantContext.paymentServiceRequired = addingPaymentVariant.paymentServiceRequired;
				paymentVariantContext.setPaymentVariant(paymentVariant);
				paymentVariant.variantName = addingPaymentVariant.name;
				paymentVariant.setContext(paymentVariantContext);
				contextManager.getPaymentVariants().add(paymentVariant);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
