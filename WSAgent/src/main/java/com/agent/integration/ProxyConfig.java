package com.agent.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.HttpResource;

import com.agent.entities.Agent;
import com.agent.entities.PolicyType;
import com.agent.entities.Service;

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
	
	public String sendRequestWSDL(Service service) {

		String wsdl = "";
	try {						
			URL obj;
			
			obj = service.getUrlWsdl();
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "AGENT");

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + obj.toString());
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//catch result
			wsdl = response.toString();

			
		} catch (MalformedURLException e) {
			throw new Error();
		} catch (IOException e) {
			throw new Error();
		}
				
		return wsdl;
		
	}


}
