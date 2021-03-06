package com.saib.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.saib.models.Transaction;
import com.saib.services.TransactionService;
import com.saib.util.Results;

@RestController
public class TransactionController {
	
	/*
	 *  GET - /transactions - Get all transactions in the database
	 *  GET - /accounts/all - Get  all  transactions in the database with pagination
	 *  GET - /transactions/all/sorted - Get all transactions in the database with pagination and sorting
	 *  POST - /transactions - Creating a new transaction 
	 *  PUT - /transaction/id - Updating an existing transaction 
	 *  DELETE -/transaction/id - for deleting an transaction from database
	 *  GET - /transaction/id - Get details for a single transaction
	 *  GET - /transactions/filter/account/{id} - Get all transaction with a given account id
	 *  GET - /transactions/filter/type - Get all transaction with a given type
	 *  GET - /transactions/filter/date - Get all transaction with a given date
	 *  GET - /transactions/filter/date&type - Get all transaction with a given date and type
	 *  
	 */
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/transactions")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions()
	{
		List<Transaction> list=transactionService.getAllTransactions();
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transactions Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		
		return response;
		
	}
	
	@GetMapping("/transactions/all")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions(@RequestParam int pageNo, @RequestParam int pageSize ){
		
		
		List<Transaction> list=transactionService.getAllTransactions(pageNo, pageSize);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transaction Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
		
	}
	
	@GetMapping("/transactions/all/sorted")
	public ResponseEntity<ApiSuccessPayload> getAllTransactions(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String sortBy ){
		
		
		List<Transaction> list=transactionService.getAllTransactions(pageNo, pageSize ,sortBy);
		HttpStatus status=HttpStatus.OK;
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Transaction Found",status);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, status);
		return response;
		
		
	}
	

	@PostMapping("/transaction")
	public ResponseEntity<ApiSuccessPayload> addTransaction(@RequestBody Transaction transaction)
	{
		ResponseEntity<ApiSuccessPayload> response=null;
		System.out.println(transaction);
		String result=transactionService.addTransaction(transaction);
		if(result.equalsIgnoreCase(Results.SUCCESS))
		{
			ApiSuccessPayload payload=ApiSuccessPayload.build(result, "Transaction created successfully", HttpStatus.CREATED);
			response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.CREATED);
			
		}
		return response;
		
	}
	
	@PutMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> updateTransaction(@RequestBody Transaction transaction, @PathVariable long transactionId)
	{
		String result=transactionService.updateTransaction(transaction, transactionId);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> deleteTransaction(@PathVariable long transactionId)
	{
		String result=transactionService.deleteTransaction(transactionId);
		ApiSuccessPayload payload=ApiSuccessPayload.build(result,result,HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}
	
	
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByTransactionNumber(@PathVariable long transactionId)
	{
		Transaction transaction=transactionService.getTransactionByTransactionNumber(transactionId);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(transaction, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/filter/account/{accountNumber}")
	public ResponseEntity<ApiSuccessPayload> getTransactionsByAccountId(@PathVariable long accountNumber)
	{
		List<Transaction> list = transactionService.getTransactionsByAccountId(accountNumber);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/filter/type/{type}")
	public ResponseEntity<ApiSuccessPayload> getTransactionByType(@PathVariable String type)
	{
		List<Transaction> list = transactionService.getTransactionsByType(type);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/filter/date/{date}")
	public ResponseEntity<ApiSuccessPayload> getTransactionsByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date)
	{
		
		List<Transaction> list = transactionService.getTransactionsByDate(date);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/transactions/filter/date&type")
	public ResponseEntity<ApiSuccessPayload> getTransactionsByDateAndType(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date , @RequestParam String type)
	{
		
		List<Transaction> list = transactionService.getTransactionsByDateAndType(date,type);
		
		ApiSuccessPayload payload=ApiSuccessPayload.build(list, "Success",HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response=new ResponseEntity<ApiSuccessPayload>(payload,HttpStatus.OK);
		return response;
	}
	
	
}
