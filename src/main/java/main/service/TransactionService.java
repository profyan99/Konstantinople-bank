package main.service;

import main.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions();

    Transaction getTransactionById(long id);

    List<Transaction> getAllTransactionsByUserName(String name);
}
