package com.saib.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saib.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	
	List<Transaction> findByTransactionType(String transactionType);

	List<Transaction> findByToAccount(long accountNumber );

	List<Transaction> findByFromAccount(long accountNumber);

	List<Transaction> findByDate(LocalDate parse);
	
}
