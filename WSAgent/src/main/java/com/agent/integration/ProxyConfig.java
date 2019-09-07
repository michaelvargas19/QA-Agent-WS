package com.agent.integration;

import java.io.File;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.HttpResource;

import com.agent.entities.Agent;
import com.agent.entities.PolicyType;

@Component("ProxyConfig")
public class ProxyConfig {
	
	//private Logger logger;
	private HttpRequest request;
	
	public String sendRegister(Agent agent) {
		
		return "";
	}
	
	public File sendRequestJAR(String URI) {
		
		return null;
	}
	
	public String sendRequestWSDL(String nameService) {
		
		return null;
	}


}
