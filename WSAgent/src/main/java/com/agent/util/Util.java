package com.agent.util;

import java.io.File;

import org.springframework.stereotype.Component;

import com.agent.entities.Agent;

@Component("Util")
public class Util {
	
	public Agent readPropiertes() {
		return null;
	}
	
	public boolean saveKey(String key) {
		return true;
	}
	
	public boolean saveJAR(File policy) {
		return true;
	}
	
	public String fixWSDL(String wsdl) {
		ModifyXML utilXML = new ModifyXML();
		return utilXML.generateWSDL("");
	}
	
	public String getRequestToWSDL(String nameService) {
		String html = "<http>" + nameService + "</http>";
		return html;
	}
	
	public String getErrorConfiguration(String nameService) {
		String html = "{Error: ' configurating " + nameService + " WSDL'}</http>";
		
		return html;
	}

}
