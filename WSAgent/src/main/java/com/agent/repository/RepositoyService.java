package com.agent.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.agent.entities.Operation;
import com.agent.entities.Policy;
import com.agent.entities.Service;

@Repository
public class RepositoyService implements IRepositoyService {
	
	private List<Service> services;
	private HashMap <String,List<Policy>> policies;

	public boolean addOperation(int id, Operation operation) {
		
		return true;
	}
	
	public boolean addPolicy(Policy policy) {
		return true;
	}
	
	public boolean addPolicy(int id, Policy policy) {
		return true;
	}
	
	public boolean addService(Service service) {
		
		for (Operation operation : service.getOperations()) {
			addOperation(service.getId(), operation);
			
		}
		
		return true;
	}
	
	public List<Policy> findPoliciesByOperation(int idOperation){
		return null;
	}
	
	public List<Policy> findPoliciesByService(Service service){
		return null;
	}
		
	
	public Service findService(int idService) {
		return null;
	}
	
	
}
