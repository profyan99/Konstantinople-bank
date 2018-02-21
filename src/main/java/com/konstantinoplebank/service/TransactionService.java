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
    Transaction findById(long id);

    List<Transaction> findByUserName(String name);

    List<Transaction> findByUserId(long id);

    List<Transaction> findByBillId(long id);

    long create(long userId, long billId, long amount, String description);

}
