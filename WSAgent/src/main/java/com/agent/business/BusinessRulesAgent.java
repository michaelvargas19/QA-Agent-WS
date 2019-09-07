package com.agent.business;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.agent.entities.Agent;
import com.agent.entities.Policy;
import com.agent.entities.Service;
import com.agent.integration.ProxyAgent;
import com.agent.integration.ProxyConfig;
import com.agent.policy.JarClassLoader;
import com.agent.repository.IRepositoyService;
import com.agent.repository.RepositoyService;
import com.agent.util.Util;

@Component("BusinessRulesAgent")
public class BusinessRulesAgent {
	
	//private Logger logger;
	
	@Autowired
	@Qualifier("Util")
	private Util util;
	
	@Autowired
	@Qualifier("ProxyAgent")
	private ProxyAgent proxyAgent;
	
	private Agent agent;
	private List<Service> services;
	private List<Policy> policies;
	
	
	
	//--------------------------------------------------------------------------- Configuration
	@PostConstruct
    public void init() {
		//loadService();
    }

	
	//--------------------------------------------------------------------------- Policies
	public Service findService(String nameService) {
		
		for (Service service : this.services) {
			if(service.getName().compareTo(nameService) == 0) {
				return service;
			}
		}
		
		return null;
	}

	private List<Policy> findPoliciesByOperation(String request) {
		return null;
	}

	public String applyPolicies(String request, List<Policy> policies) {
		
		String requestFixed = request;
		
		for (Policy policy : policies) {
			policy.applyPolitic(requestFixed);			
		}
		
		return requestFixed;
	}
		
	
	private boolean validatePolicies(String request) {
		return true;
	}
	
		
	
	//---------------------------------------------------------------------------


	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	};
	
	

}
