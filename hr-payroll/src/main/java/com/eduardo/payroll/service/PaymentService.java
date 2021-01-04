/**
 * 
 */
package com.eduardo.payroll.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eduardo.payroll.entities.Payment;
import com.eduardo.payroll.entities.Worker;

/**
 * @author user
 *
 */

@Service
public class PaymentService {
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	public Payment getPayment(long workId, int days) {
		
		Map<String, Long> uriVariables = new HashMap<>();
		
		uriVariables.put("id", workId);
		
		Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
