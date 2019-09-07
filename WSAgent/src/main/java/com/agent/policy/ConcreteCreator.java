package com.agent.policy;

import org.springframework.stereotype.Component;

import com.agent.entities.PolicyType;

@Component("ConcreteCreator")
public class ConcreteCreator {
	
	public IPolicy getPolicies(PolicyType type) {
		return null;
	}

}
