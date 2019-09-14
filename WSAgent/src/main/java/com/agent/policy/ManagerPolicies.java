package com.agent.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.agent.entities.Policy;
import com.agent.entities.PolicyType;

@Service("ManagerPolicies")
public class ManagerPolicies implements IManagerPolicies {
	
	private List<Policy> policies;
	private IPolicy policy;
	
	@Autowired
	@Qualifier("PoliciesFactory")
	private PoliciesFactory policiesFactory;
	
	
	
	//<Interface>------------------------------
	
	@Override
	public boolean reloadPolicies(List<Policy> policies) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadPolicies(Policy policy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePolicies(List<Policy> Policies) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String applyPolicy(String request, PolicyType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processPolicy(String request, PolicyType type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//<Getters and Setters>------------------------------

}
