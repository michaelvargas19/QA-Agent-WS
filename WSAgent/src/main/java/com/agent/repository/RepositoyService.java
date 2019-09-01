package com.agent.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.agent.entities.Policy;
import com.agent.entities.Service;

@Repository
public class RepositoyService implements IRepositoyService {

	public List<Policy> findPoliciesByService(Service service){
		return null;
	}
}
