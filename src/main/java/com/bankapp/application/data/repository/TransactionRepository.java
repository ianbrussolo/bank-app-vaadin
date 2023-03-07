package com.bankapp.application.data.repository;

import com.bankapp.application.data.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
