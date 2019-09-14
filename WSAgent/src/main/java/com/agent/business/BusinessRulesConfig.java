package com.agent.business;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.agent.entities.Agent;
import com.agent.entities.Policy;
import com.agent.entities.ResponseConfiguration;
import com.agent.entities.Service;
import com.agent.integration.ProxyAgent;
import com.agent.integration.ProxyConfig;
import com.agent.policy.IManagerPolicies;
import com.agent.policy.JarClassLoader;
import com.agent.repository.IRepositoyService;
import com.agent.repository.RepositoyService;
import com.agent.util.Util;

@Component("BusinessRulesConfig")
public class BusinessRulesConfig {
	
	//private Logger logger;
	
	@Autowired
	@Qualifier("Util")
	private Util util;
	
	@Autowired
	private RepositoyService repository;
	
	@Autowired
	@Qualifier("ProxyConfig")
	private ProxyConfig proxyConfig;
	
	@Autowired
	@Qualifier("ManagerPolicies")
	private IManagerPolicies managerPolicies;
		
	private Agent agent;
	private List<Service> services;
	private List<Policy> policies;	
	
	
	public void configureAgent() {
		
		this.agent = this.util.getAgent();
		Boolean stateConf = false;
		
		while(stateConf) {
			
			ResponseConfiguration response = this.proxyConfig.sendRegister(agent);
			
			this.util.saveKey(response.getPrivateKey(), response.getPublicKey());
			
			this.services = response.getServices();
			this.policies = findPolicies(response);
			
			List<File> files = new ArrayList<File>();
			
			//Validate policies
			for (Policy policy : this.policies) {
				
				File jar = this.proxyConfig.sendRequestJAR(policy.getURLJar());
				
				if(validateSignature(jar)) {
					files.add(jar);
					stateConf = true;
				}else{
					files.clear();
					stateConf = false;
					break;
				};
				
			}
			
			//Save policies JAR
			for (File jar : files) {
				this.util.saveJAR(jar);
			}
			
			//Load policies and Save datas			
			for (Policy policy : policies) {
				this.repository.addPolicy(policy);
				this.managerPolicies.loadPolicies(policy);	
			}
			
			for (Service service : services) {
				
				String wsdl ="";
				
				wsdl = this.proxyConfig.sendRequestWSDL(service);
				
				wsdl = this.util.fixWSDL(wsdl);
				
				this.util.saveWSDL(wsdl);
				
				this.repository.addService(service);
				
			}
			
			
		}
			
	}
	
	
		
	private List<Policy> findPolicies(ResponseConfiguration response) {
		return null;
	};	
	
	private boolean validateSignature(File jar) {
		return true;
	}
	
	/**/
	public String getWSDL(String nameService) {
		
		String wsdl ="Error";
		
		Service service = this.repository.findService(nameService);
		
		wsdl = this.proxyConfig.sendRequestWSDL(service);
		
		wsdl = this.util.fixWSDL(wsdl);
		
		return wsdl;
	}
	
	//--------------------------------------------------------------------------- Configuration
	@PostConstruct
    public void init() {
		configureAgent();
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
