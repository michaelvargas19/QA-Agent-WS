package com.agent.policy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agent.entities.Policy;
import com.agent.entities.PolicyType;

public interface IManagerPolicies {
	
	boolean reloadPolicies(List<Policy> policies);
	boolean loadPolicies(Policy policy);
	boolean updatePolicies(List<Policy> Policies);
	String applyPolicy(String request, PolicyType type );
	String processPolicy(String request, PolicyType type);



}
