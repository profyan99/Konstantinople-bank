package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Transaction;

import java.util.List;

public interface TransactionMapper {
    Transaction findTransactionById(long id);

    List<Transaction> findTransactionsByUserId(long id);

    void createTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);
}
