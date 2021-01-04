/**
 * 
 */
package com.eduardo.payroll.service;

import org.springframework.stereotype.Service;

import com.eduardo.payroll.entities.Payment;

/**
 * @author user
 *
 */

@Service
public class PaymentService {
	
	public Payment getPayment(long workId, int days) {
		
		
		
		return new Payment("Bob", 200.0, days);
	}

}
