package com.eduardo.hroauth.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eduardo.hroauth.entities.User;


//componente gerenciado pelo Spring
@Component
@FeignClient(name = "hr-user", path = "/users")
public interface UserfeignClient {
	
	@GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);

}
