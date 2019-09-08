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
		
	private Agent agent;
	private List<Service> services;
	private List<Policy> policies;
	
	
	
	public void loadService() {
		
		this.agent = this.util.getAgent();
		
		String response = this.proxyConfig.sendRegister(agent);
		
		String prk = getPrimaryKeyAgent(response);
		String puk = getPublicKeyWSG(response);
		List<Service> services = getServices(response);
		List<Policy> policies = getPolicies(response);
		
		this.util.saveKey(prk, puk);
		
		/*for (Policy policy : policies) {
			
			File jar = this.proxyConfig.sendRequestJAR(policy.getURLJar());
			policy.setJar(jar);
		}
		
		for (Policy policy : policies) {
			
			validateSignature(policy.getJar());
		}
			
		try {
			JarClassLoader loader = new JarClassLoader(new URL(""));
			
			for (Policy policy : policies) {
				this.util.saveJAR(policy.getJar());
				this.repository.addPolicy(policy);
				loader.loadJar();
			}
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		
		for (Service service : services) {
			this.repository.addService(service);
		}*/
			
		
		
	}
	
	private String getPrimaryKeyAgent(String response) {
		return "";
	};
	
	private String getPublicKeyWSG(String response) {
		return "";
	};
	
	private List<Service> getServices(String response) {
		return null;
	};
	
	private List<Policy> getPolicies(String response) {
		return null;
	};	
	
	private boolean validateSignature(File jar) {
		return true;
	}
	
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
		loadService();
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
