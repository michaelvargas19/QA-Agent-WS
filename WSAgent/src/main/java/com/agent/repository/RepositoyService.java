package com.agent.repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
			for (Policy policy : operation.getPolicies()) {
				addPolicy(operation.getId(), policy);
			}
		}
		
		return true;
	}
	
	public List<Policy> findPoliciesByOperation(int idOperation){
		return new ArrayList<Policy>();
	}
	
	public List<Policy> findPoliciesByService(Service service){
		return new ArrayList<Policy>();
	}
		
	
	public Service findService(String nameservice) {
		
		Service service = new Service();
		
		try {
			
			service.setUrlWsdl(new URL("https://svn.apache.org/repos/asf/airavata/sandbox/xbaya-web/test/Calculator.wsdl"));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return service;
	}
	
	
}
