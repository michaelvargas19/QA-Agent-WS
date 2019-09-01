package com.agent.repository;

import java.util.List;

import com.agent.entities.Policy;
import com.agent.entities.Service;

public interface IRepositoyService {
	
	List<Policy> findPoliciesByService(Service service);

}
