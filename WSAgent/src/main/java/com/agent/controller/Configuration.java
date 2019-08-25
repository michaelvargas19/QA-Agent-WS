package com.agent.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agent.util.AppConfig;
import com.agent.util.ModifyXML;

@RestController
@RequestMapping("configuration")
public class Configuration {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	//Resource type GET to consult a WSDL
	@RequestMapping(value = "/wsdl", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String getConfiguration() {
		ModifyXML editor = context.getBean("modifyXML", ModifyXML.class);
		String wsdl = editor.generateWSDL("");
		return wsdl;
	}

	
		
}
