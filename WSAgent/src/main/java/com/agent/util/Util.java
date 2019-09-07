package com.agent.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.agent.entities.Agent;

@Component("Util")
public class Util {
	
	private Agent agent;
	
	public Agent readPropiertes() {
		
		try {
			
			Properties configuratio = new Properties();
			InputStream input = new FileInputStream("./conf.properties");
			
			configuratio.load(input);
			this.agent = new Agent(configuratio.getProperty("ipGuardian"),0,"");
			
			return this.agent;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		
		return null;
	}
	
	@PostConstruct
    public void init() {
		
		readPropiertes();
		
    }
	
	
	public boolean saveKey(String primaryKey, String publicKeyWSG) {
		return true;
	}
	
	public boolean saveJAR(File policy) {
		return true;
	}
	
	public String fixWSDL(String wsdl) {
		
		//The class ModifyXML and its method generateWSDL are used like example,
		//In the future it is going to be a algorimt to fix the wsdl location's services(parameter)
		//with a local url
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
	
	
	//---------------------------------------------------------------------------

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	

}
