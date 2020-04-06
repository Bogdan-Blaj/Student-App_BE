package com.appstudent.app.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
	
	@Autowired //needed for the env object to be available
	private Environment env;
	
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
}
