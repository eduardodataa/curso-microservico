package com.eduardo.hroauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.hroauth.entities.User;
import com.eduardo.hroauth.feignClients.UserfeignClient;

@Service
public class UserService {

	@Autowired
	private UserfeignClient userfeignClient;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public User findByEmail(String email) {
		User user = userfeignClient.findByEmail(email).getBody();
		if(user == null) {
			logger.error("Email não contrado");
			throw new IllegalArgumentException("Email não encontrado");
		}
		logger.info("E-mail encontrado : " + email);
		return user;
	}
	
}
