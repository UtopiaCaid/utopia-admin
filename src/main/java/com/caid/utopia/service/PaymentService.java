package com.caid.utopia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caid.utopia.entity.Payment;
import com.caid.utopia.repo.PaymentRepo;

import exception.RecordNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepo paymentRepo;
	
	public List<Payment> getAllPayments() throws RecordNotFoundException {
		try {
			List<Payment> payments = paymentRepo.findAll();
			if(payments.isEmpty()) {
				throw new RecordNotFoundException();
			}
			return payments;
		} catch(Exception e) {
			throw e;
		}
	}
	
}
