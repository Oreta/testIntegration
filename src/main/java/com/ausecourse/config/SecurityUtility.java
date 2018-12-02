package com.ausecourse.config;

import java.security.SecureRandom;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;

import com.ausecourse.dao.UserSecurityDao;

@Component
public class SecurityUtility {
	
	private static final String SALT = "salt" ; //salt should be protected carefully
	
	@Autowired
	private UserSecurityDao userSecurityService;
	


	
	@Bean
	public static String randomPassword(){
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		
		while(salt.length()<18){
			int index =(int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.toString());
		}
		
		String saltstr =salt.toString() ; 
		return saltstr ; 
	}
}
