package com.appstudent.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appstudent.app.ws.SpringApplicationContext;
import com.appstudent.app.ws.service.StudentService;
import com.appstudent.app.ws.shared.dto.StudentDTO;
import com.appstudent.app.ws.ui.model.request.StudentLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


//the authentication filter cant autowire or inject other filters
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			StudentLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),
					StudentLoginRequestModel.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	protected void successfulAuthentication (HttpServletRequest req, HttpServletResponse res, 
			FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		String userName = ((User) auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();
		
		//used to call the methods of create student, get student etc.
		//get publicID by quering the DB to a dedicated API 
		//The Spring Application Contexts needs to be available as the Bean, add it in the main function
		StudentService studentService = (StudentService) SpringApplicationContext.getBean("studentServiceImpl");
		StudentDTO studentDTO = studentService.getStudent(userName); //username is the email provided in logIn
		
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("StudentID", studentDTO.getStudentId());
		
		
	}
}
