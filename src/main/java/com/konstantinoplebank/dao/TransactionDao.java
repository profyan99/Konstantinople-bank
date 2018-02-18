package com.konstantinoplebank.dao;

import com.konstantinoplebank.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionDao{
    Optional<Transaction> findTransactionById(long id);

    List<Transaction> findTransactionsByUserId(long id);

    void createTransaction(Transaction transaction);
}
