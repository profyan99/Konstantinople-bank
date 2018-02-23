package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.utils.exception.InvalidTransaction;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link Transaction}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

public interface TransactionService {
    Optional<Transaction> findById(long id, long billId, long userId);

    List<Transaction> findByUserName(String name);

    List<Transaction> findByUserId(long id);

    List<Transaction> findByBillId(long id);

    List<Transaction> findAll();

    Transaction create(long userId, long billId, long amount, String description) throws InvalidTransaction;

}
