package com.agent.business;

import java.util.List;

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
	private RepositoyService repository;
	
	private ProxyConfig proxyConfig;
	
	@Autowired
	private ProxyAgent proxyAgent;
	
	private Agent agent;
	private List<Service> services;
	private List<Policy> policies;
	
	public void loadSeervice() {}
	
	private void getPrimaryKey(String response) {};
	
	private List<Service> getServices(String response) {
		return null;
	};
	
	private List<Policy> getPolicies(String response) {
		return null;
	}
	
	private boolean validateSignature() {
		return true;
	}
	
	public String getWSDL(String nameService) {
		
		String html = this.util.getRequestToWSDL(nameService);
		
		String wsdl = this.proxyAgent.sendRequest(html);
		
		wsdl = this.util.fixWSDL(wsdl);
		
		return wsdl;
	}

	//--------------------------------------------------------------------------- Policies
	public String applyPolicies(String request, List<Policy> policies) {
		
		String requestFixed = request;
		
		for (Policy policy : policies) {
			policy.applyPolitic(requestFixed);			
		}
		
		return requestFixed;
	}
	
	public 	String processRequest(String request, int idService) {
		
		Service service = this.findService("#nameService");
		
		List<Policy> policies = this.repository.findPoliciesByService(service);
		
		this.applyPolicies(request, policies);
		
		return "";		
	}
	
	public Service findService(String nameService) {
		
		for (Service service : this.services) {
			if(service.getName().compareTo(nameService) == 0) {
				return service;
			}
		}
		
		return null;
	}
	
	
	//---------------------------------------------------------------------------
	
	public RepositoyService getRepository() {
		return repository;
	}

	public void setRepository(RepositoyService repository) {
		this.repository = repository;
	}

	public ProxyConfig getProxy() {
		return proxyConfig;
	}

	public void setProxy(ProxyConfig proxy) {
		this.proxyConfig = proxy;
	}

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
