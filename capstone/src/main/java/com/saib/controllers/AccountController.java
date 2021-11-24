package com.saib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSuccessPayload;
import com.saib.models.Account;
import com.saib.services.AccountService;


@RestController
public class AccountController {

	/***
	 * GET - /accounts - Get me all details
	 * GET - /accounts/id -
	 * POST - /accounts -
	 * PUT - /accounts/id
	 * DELETE - /accounts/id
	 */
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/accounts")
	public ResponseEntity<ApiSuccessPayload> getAllAccounts(){
		List<Account> list = accountService.getAllAccount();
		
		ApiSuccessPayload payload = ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> res= new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return res;
		
	}
	
}
