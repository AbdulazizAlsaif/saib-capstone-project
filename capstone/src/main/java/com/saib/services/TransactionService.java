package com.saib.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import com.saib.config.ApiSuccessPayload;
import com.saib.models.Account;
import com.saib.models.Transaction;
import com.saib.repository.AccountRepository;
import com.saib.repository.TransactionRepository;
import com.saib.util.Results;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	AccountRepository accountRepository;
	
	

	public List<Transaction> getAllTransactions()
	{
		List<Transaction> list=transactionRepository.findAll();
		return list;
	}
		

	public List<Transaction> getAllTransactions(int pageNo, int pageSize) {


		
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Transaction> pageResult=transactionRepository.findAll(paging);
		int total = pageResult.getTotalPages();
		System.out.println(total);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}
		else
			return new ArrayList<Transaction>();
		
	}
	
	public List<Transaction> getAllTransactions(int pageNo, int pageSize, String sortBy) {


		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Transaction> pageResult=transactionRepository.findAll(paging);
		int total = pageResult.getTotalPages();
		System.out.println(total);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}
		else
			return new ArrayList<Transaction>();
		
	}


	
	public String addTransaction(Transaction transaction)
	{
		String result="";
		Optional<Account> fromAccount = accountRepository.findById(transaction.getFromAccount());
		Optional<Account> toAccount = accountRepository.findById(transaction.getToAccount());
		
		if(!fromAccount.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "From Account does not exits");
		}
		if(!toAccount.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "To Account does not exits");
		}
		
		double newFromAccountBalance = fromAccount.get().getBalance()-transaction.getAmount();
		double newToAccountBalance = toAccount.get().getBalance()+transaction.getAmount();
		if(newFromAccountBalance >= 0) {
		fromAccount.get().setBalance(newFromAccountBalance);
		toAccount.get().setBalance(newToAccountBalance);
		
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "insufficent funds");
		}
		
		accountRepository.save(fromAccount.get());
		accountRepository.save(toAccount.get());
		
		Transaction storedTransaction=transactionRepository.save(transaction);
		if(storedTransaction!=null) {
			result=Results.SUCCESS;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction not created");
		}
		
		return result;
	}
	
	public String updateTransaction(Transaction transaction, long transactionNumber)
	{
		String result="";
		
		transaction.setTransactionId(transactionNumber);
		Transaction updatedTransaction=transactionRepository.save(transaction);
		
		if(updatedTransaction!=null)
		{
			result=Results.SUCCESS;
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record was not updated");
		}
		return result;
		
	}
	
	public String deleteTransaction(long transactionNumber)
	{
		String result="";
		try {
		transactionRepository.deleteById(transactionNumber);
		
		
			result=Results.SUCCESS;
			return result;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	
	
	public Transaction getTransactionByTransactionNumber(long transactionNumber)
	{
		Optional<Transaction> optional=transactionRepository.findById(transactionNumber);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction with Transaction Number:"+transactionNumber+"doesn't exist");
		}
		
	}

	public List<Transaction> getTransactionsByAccountId(long accountNumber)
	{
		List<Transaction> list=transactionRepository.findByToAccount(accountNumber);
		list.addAll(transactionRepository.findByFromAccount(accountNumber));

		if(!list.isEmpty())
			return list;
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction With Account number " + accountNumber + " does not exits");
		}
		
		
	}
	
	public List<Transaction> getTransactionsByType(String type)
	{
		List<Transaction> list=transactionRepository.findByTransactionType(type);
		if(!list.isEmpty())
			return list;
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction With type " + type + " does not exists");
		}
		
		
	}
	
	public List<Transaction> getTransactionsByDate(LocalDate date) {
			
			List<Transaction> list=transactionRepository.findByDate(date);
	
			if(!list.isEmpty())
				return list;
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transaction With given date does not exists");
			}
			
	}

	public List<Transaction> getTransactionsByDateAndType(LocalDate date, String type) {
		
		List<Transaction> list=transactionRepository.findByTransactionTypeAndDate(type, date);
		
		if(!list.isEmpty())
			return list;
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Transactions With the provdid input does not exists");
		}
		

	}
	

}

