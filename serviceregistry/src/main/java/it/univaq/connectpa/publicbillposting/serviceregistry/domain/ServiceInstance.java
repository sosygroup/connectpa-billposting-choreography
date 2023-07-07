package it.univaq.connectpa.publicbillposting.serviceregistry.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ServiceInstance extends AbstractPersistable<Long> implements Serializable {

	private static final long serialVersionUID = -1012382449574845645L;

	private String serviceName;

	private String instanceName;

	private String baseURL;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

}
