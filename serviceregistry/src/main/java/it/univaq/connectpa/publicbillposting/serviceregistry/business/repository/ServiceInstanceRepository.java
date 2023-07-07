package it.univaq.connectpa.publicbillposting.serviceregistry.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.connectpa.publicbillposting.serviceregistry.domain.ServiceInstance;

@Repository
public interface ServiceInstanceRepository extends JpaRepository<ServiceInstance, Long>{

	ServiceInstance findByServiceNameAndInstanceName(String serviceName, String instanceName);

	List<ServiceInstance> findByServiceName(String serviceName);

	void deleteByServiceNameAndInstanceName(String serviceName, String instanceName);
}
