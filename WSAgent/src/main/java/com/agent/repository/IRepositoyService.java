package com.agent.repository;

import java.util.List;

import com.agent.entities.Operation;
import com.agent.entities.Policy;
import com.agent.entities.Service;

public interface IRepositoyService {
	
	List<Policy> findPoliciesByService(Service service);
	boolean addService(Service service);
	boolean addOperation (int idService,Operation opetation);
	boolean addPolicy(int idOperation, Policy policie);
	boolean addPolicy(Policy policy);
	Service findService(int idService);
	List<Policy> findPoliciesByOperation(int idOperation); 

}
