package it.univaq.connectpa.publicbillposting.sia.endpoints.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import it.univaq.connectpa.publicbillposting.sia.endpoints.client.impl.SiaEndpointsClientImpl;

@ComponentScan(basePackages = "it.univaq.incipict.mid1.sia.endpoints.client")
public class SiaEndpointsClientApp {

	@Autowired
	private SiaEndpointsClientImpl siaEndpointsClientImpl;

	@Value("${test}")
	private String test;

    public static void main(String[] args) {

    	ApplicationContext context = new AnnotationConfigApplicationContext(SiaEndpointsClientApp.class);

    	SiaEndpointsClientApp app = context.getBean(SiaEndpointsClientApp.class);
        app.start(args);

        ((ConfigurableApplicationContext)context).close();
    }

    private void start(String[] args) {
    	siaEndpointsClientImpl.setConfigurationAddress();
    }
}
