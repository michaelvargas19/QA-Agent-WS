package com.agent.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agent.util.AppConfig;
import com.agent.util.ModifyXML;

@RestController
@RequestMapping("operations")
public class OperationsResource {
	
	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	//Resource type GET to receive any request 
	@RequestMapping(value = "/**", method = RequestMethod.GET)
	@ResponseBody
	public String operationGET(HttpServletRequest request) {
		StringBuffer path = request.getRequestURL();
		String headerNames = "";
		Enumeration<String> names = request.getHeaderNames();
		
		while(names.hasMoreElements()) {
			headerNames = headerNames + names.nextElement() + " ---- ";
		}
		
		return "Path: " + path.toString() +"\r\n         "+
				"HeaderNames:" + headerNames;
	}
	
	//Resource type POST to receive any SOAP request
	@RequestMapping(value = "/**", method = RequestMethod.POST, consumes = "text/xml")
	@ResponseBody
	public String operationPOST(@RequestHeader(value="soapAction") String soapAction, @RequestBody String content) {
		
		return soapAction;
	}
}
