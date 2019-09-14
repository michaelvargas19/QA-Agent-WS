package com.agent.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agent.business.BusinessRulesAgent;
import com.agent.business.BusinessRulesConfig;
import com.agent.entities.Service;
import com.agent.util.ModifyXML;
import com.agent.util.Util;


@RestController
public class WSAgent {

	private List<Service> services;
	
	@Autowired
	@Qualifier("BusinessRulesAgent")
	private BusinessRulesAgent business;
	
	@Autowired
	@Qualifier("BusinessRulesConfig")
	private BusinessRulesConfig configuration;
	
	private Util util;
		
	
	//Resource type GET to consult a WSDL
	@RequestMapping(value = "configuration/**", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String getConfiguration(HttpServletRequest request) {

		String wsdl = "Error" ;
		String nameService = "";
		try {
			
			StringBuffer path = request.getRequestURL();
			URL url = new URL(path.toString());
			nameService = url.getPath().substring(15);
			
			if(validateService(nameService)) {
				wsdl = configuration.getWSDL(nameService);
			}else {
				wsdl = util.getErrorConfiguration(nameService);
			}
			
			
		} catch (MalformedURLException e) {
			wsdl = util.getErrorConfiguration(nameService);
		}
		
		return wsdl;
		
	}

	
	//Resource type GET to receive any request 
	@RequestMapping(value = "operations/**", method = RequestMethod.GET)
	@ResponseBody
	public String operationGET(HttpServletRequest request) {
		
		StringBuffer path = request.getRequestURL();
		
		String response = this.business.processRequest(request.toString(), path.toString());
		
		return response + "\r\n" + path.substring(33);
	}
	
	//Resource type POST to receive any SOAP request
	@RequestMapping(value = "operations/**", method = RequestMethod.POST, consumes = "text/xml")
	@ResponseBody
	public String operationPOST(HttpServletRequest request) {
		
		
				
		return request.toString();
	}
		
	private boolean validateService(String name) {
		return true;
	}
	
	@PostConstruct
    public void init() {
        this.services = this.business.getServices();
    }

	//-----------------------------------------------------------------------
	
	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public BusinessRulesAgent getBusiness() {
		return business;
	}

	public void setBusiness(BusinessRulesAgent business) {
		this.business = business;
	}

	
}
