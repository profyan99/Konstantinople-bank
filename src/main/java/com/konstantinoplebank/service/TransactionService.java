package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link Transaction}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

public interface TransactionService {
    Optional<Transaction> findById(long id);

    List<Transaction> findByUserName(String name);

    List<Transaction> findByUserId(long id);

    List<Transaction> findByBillId(long id);

    List<Transaction> findAll();

    long create(long userId, long billId, long amount, String description);

}
