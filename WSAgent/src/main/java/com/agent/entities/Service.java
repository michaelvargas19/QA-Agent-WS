package com.agent.entities;

import java.net.URL;
import java.util.Date;
import java.util.List;

public class Service {
	private int id;
	private String name;
	private String description;
	private URL urlWsdl;
	private String version;
	private Date expiration;
	private List<Operation> operations;
	
	//--------------------------------------------------------
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public URL getUrlWsdl() {
		return urlWsdl;
	}
	public void setUrlWsdl(URL urlWsdl) {
		this.urlWsdl = urlWsdl;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	
	

	
	
}
