package com.agent.entities;

public class Agent {
	
	private String ipGuardian;
	private int portGuardian;
	private String ipAgent;
	
		
	public Agent(String ipGuardian, int portGuardian, String ipAgent) {
		super();
		this.ipGuardian = ipGuardian;
		this.portGuardian = portGuardian;
		this.ipAgent = ipAgent;
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
	
	

}
