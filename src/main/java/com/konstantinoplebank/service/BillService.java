package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface BillService {
    Optional<Bill> findById(long id);

    List<Bill> findByUserId(long id);

    List<Bill> findByUserName(String name);

    List<Bill> findAll();

    long create(long userId, long amount);

    void applyTransaction(Transaction transaction);

    void updateAmount(long billId, long amount);
}
