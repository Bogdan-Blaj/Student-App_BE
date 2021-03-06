package com.appstudent.app.ws.shared;

import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Utils {
	
	private final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
	private final Random random = new Random();
	
	public String generateStudentId(int lenght) {
		return new String(generateRandomString(lenght));
	}
	
	public String generateCourseId(int lenght) {
		return new String(generateRandomString(lenght));
	}
	
	private String generateRandomString(int lenght) {
		StringBuilder returnValue = new StringBuilder(lenght);
		for (int i = 0; i < lenght; i++) {
			returnValue.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		return new String(returnValue);
	}
}
