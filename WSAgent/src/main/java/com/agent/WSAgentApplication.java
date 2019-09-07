package com.agent;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.agent.business.BusinessRulesAgent;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic;



@SpringBootApplication
//@ComponentScan({"com.agent.business", "com.agent.controller"})
public class WSAgentApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(WSAgentApplication.class, args);
		
	}
	
	
}
