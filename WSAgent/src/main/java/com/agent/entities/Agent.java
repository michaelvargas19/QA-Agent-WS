package com.agent.entities;

public class Agent {
	
	private String ipGuardian;
	private int portGuardian;
	private String ipAgent;
	private int portAgent;
	
		
	public Agent(String ipGuardian, int portGuardian, String ipAgent, int portAgent) {
		super();
		this.ipGuardian = ipGuardian;
		this.portGuardian = portGuardian;
		this.ipAgent = ipAgent;
		this.portAgent = portAgent;
	}



	public String getIpGuardian() {
		return ipGuardian;
	}
	public void setIpGuardian(String ipGuardian) {
		this.ipGuardian = ipGuardian;
	}
	public String getIpAgent() {
		return ipAgent;
	}
	public void setIpAgent(String ipAgent) {
		this.ipAgent = ipAgent;
	}



	public int getPortGuardian() {
		return portGuardian;
	}



	public void setPortGuardian(int portGuardian) {
		this.portGuardian = portGuardian;
	}



	public int getPortAgent() {
		return portAgent;
	}



	public void setPortAgent(int portAgent) {
		this.portAgent = portAgent;
	}
	
	

}
