package com.agent.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.agent.entities.PolicyType;

@Component("PoliciesFactory")
public  class PoliciesFactory {
	
	@Autowired
	@Qualifier("ConcreteCreator")
	private ConcreteCreator concreteCreator;
	
	public IPolicy getPolicies(PolicyType type) {
		return this.concreteCreator.getPolicies(type);
	}

}
