package com.agent.entities;

import java.util.List;

public class Operation {
	
	private int id;
	private String name;
	private Service service;
	private List<Policy> policies;
	
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
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public List<Policy> getPolicies() {
		return policies;
	}
	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}
	
	

}
