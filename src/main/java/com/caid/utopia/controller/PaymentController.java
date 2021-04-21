package com.caid.utopia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caid.utopia.entity.Payment;
import com.caid.utopia.service.PaymentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/admin")
@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	/* get all records*/
	@RequestMapping(value = "/Payment", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Payment>> getAllPayments(){
		List<Payment> payments = paymentService.getAllPayments();
		if( payments.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(payments, HttpStatus.OK);
		}
	}
}
