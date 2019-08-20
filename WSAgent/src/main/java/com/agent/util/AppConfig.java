package com.agent.util;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
	
	//Declarate Bean modifyXML Singleton
	@Bean("modifyXML")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ModifyXML getModifyXML() {
	      return new ModifyXML();
	}
	

}
