package com.agent.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.agent.business.BusinessRulesAgent;

@Component("ProxyAgent")
public class ProxyAgent {
	
	@Autowired
	@Qualifier("BusinessRulesAgent")
	private BusinessRulesAgent business;
	
	public String sendRequest(String http) {
		return http;		
	}

}
