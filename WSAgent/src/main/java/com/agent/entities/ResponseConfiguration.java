package com.agent.entities;

import java.io.File;
import java.util.List;

public class ResponseConfiguration {
	
	private List<Service> services;
	private String privateKey;
	private String publicKey;
	
	//----------------------------------------------------------------
	
	
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

		
	
}
