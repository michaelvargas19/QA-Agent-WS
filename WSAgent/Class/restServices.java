package com.agent.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class restServices {
	
	@RequestMapping(name = "/restService", method = RequestMethod.GET)
	 @ResponseBody
	public String restServiceG() {
		return "This is the REST Service";
	}
	
	@RequestMapping(name = "/restServiceP", method = RequestMethod.POST)
	@ResponseBody
	public String restServiceP() {
		return "This is the REST Service";
	}
	
	

}
