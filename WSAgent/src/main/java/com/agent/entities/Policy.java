package com.agent.entities;

import java.util.Date;

public class Policy {
	
	private int id;
	private String name;
	private Date expiration;
	private PolicyType type;
	
	public String applyPolitic(String request) {
		return " :: " + request + "--> Have polity " + this.name;
	}

	//--------------------------------------
	
	
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

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public PolicyType getType() {
		return type;
	}

	public void setType(PolicyType type) {
		this.type = type;
	}

	
}
