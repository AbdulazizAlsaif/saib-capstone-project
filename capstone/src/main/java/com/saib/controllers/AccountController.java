package com.saib.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saib.config.ApiSuccessPayload;
import com.saib.models.Account;
import com.saib.services.AccountService;
import com.saib.util.Results;

@RestController
public class AccountController {
	
	/*
	 *  POST - /accounts - Creating a new account
	 *  GET - /accounts - Get  all  accounts in the database
	 *  GET - /accounts/all - Get  all  accounts in the database with pagination
	 *  GET - /accounts/all/sorted - Get  all  accounts in the database with pagination and sort
	 *  GET - /accounts/{id} - Get me details for a single account
	 *  PUT - /accounts/{id} - Updating an existing account 
	 *  DELETE -/accounts/{id}- for deleting an account from db
	 *  GET - /accounts/filter/status - Get all accounts with a given status
	 *  GET - /accounts/filter/type - Get all accounts with a given type
	 *  GET - /accounts/filter/gender - Get all accounts with a given gender
	 *  
	 */
	@Autowired
	AccountService accountService;
	
	
	@GetMapping("/accounts")
	public ResponseEntity<ApiSuccessPayload> getAllAccounts()
	{
		List<Account> list=accountService.getAllAccount();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/accounts/all")
	public ResponseEntity<ApiSuccessPayload> getAllAccounts(@RequestParam int pageNo, @RequestParam int pageSize ){
		
		
		List<Account> list=accountService.getAllAccounts(pageNo, pageSize);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
		
	}
	
	@GetMapping("/accounts/all/sorted")
	public ResponseEntity<ApiSuccessPayload> getAllAccounts(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortBy ){
		
		
		List<Account> list=accountService.getAllAccounts(pageNo, pageSize ,sortBy);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
		
	}
	
	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> getAccountbyAccountNumber(@PathVariable long accountNumber)
	{
		Account account=accountService.getAccountByAccountNumber(accountNumber);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(account, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
	@PostMapping("/account")
	public ResponseEntity<ApiSuccessPayload> addAccount(@RequestBody Account account)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(account);
		String result=accountService.addAccount(account);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Account created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
		}
		
		return response;
	
	}
	
	@PutMapping("/account/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> updateAccount(@RequestBody Account account, @PathVariable long accountNumber)
	{
		String result=accountService.updateAccount(account, accountNumber);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/account/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> deleteAccount(@PathVariable long accountNumber)
	{
		String result=accountService.deleteAccount(accountNumber);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/accounts/filter/status/{status}")
	public ResponseEntity<ApiSuccessPayload> getAccountByStatus(@PathVariable String status){
		
		List<Account> list = accountService.getAccountsByStatus(status);
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
		
	}
	
	@GetMapping("/accounts/filter/type/{type}")
	public ResponseEntity<ApiSuccessPayload> getAccountsByType(@PathVariable String type)
	{
		List<Account> list = accountService.getAccountsByType(type);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/accounts/filter/gender/{gender}")
	public ResponseEntity<ApiSuccessPayload> getAccountByGender(@PathVariable String gender)
	{
		List<Account> list=accountService.getAccountsByGender(gender);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Accounts Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
	}

	


}