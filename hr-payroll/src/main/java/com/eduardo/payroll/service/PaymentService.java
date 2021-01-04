/**
 * 
 */
package com.eduardo.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.payroll.entities.Payment;
import com.eduardo.payroll.entities.Worker;
import com.eduardo.payroll.feignclients.WorkerFeignClient;

/**
 * @author user
 *
 */

@Service
public class PaymentService {
	
	//@Value("${hr-worker.host}")
	//private String workerHost;
	
	@Autowired
	private WorkerFeignClient workerFeignClient; 
	
	public Payment getPayment(long workId, int days) {
		
		Worker worker = workerFeignClient.findById(workId).getBody();
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
