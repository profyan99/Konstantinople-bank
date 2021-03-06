package com.konstantinoplebank.dao;

import com.konstantinoplebank.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionDao{
    Optional<Transaction> findTransactionById(long id, long billId, long userId);

    List<Transaction> findTransactionsByUserId(long id);

    List<Transaction> findTransactionsByBillId(long id);

    List<Transaction> findTransactionsByUserName(String name);

    List<Transaction> findAll();

    Transaction createTransaction(Transaction transaction);
}
