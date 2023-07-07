package it.univaq.connectpa.publicbillposting.sia.endpoints.client.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:endpoints.properties")
public class SiaEndpointsClientProperties {

    @Autowired
    private Environment env;

    public String getValue(String key){
        return env.getProperty(key);
    }
}
