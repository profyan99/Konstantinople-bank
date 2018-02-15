package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Transaction;

import java.util.List;

/**
 * Service class for {@link Transaction}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

public interface TransactionService {
    List<Transaction> getAllTransactions();

    Transaction getTransactionById(long id);

    List<Transaction> getAllTransactionsByUserName(String name);
}
