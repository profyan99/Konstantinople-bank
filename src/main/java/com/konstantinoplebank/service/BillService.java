package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface BillService {
    Optional<Bill> findBillById(long id);

    List<Bill> findBillsByUserId(long id);

    long createBill(long userid, long amount);

    void applyTransaction(Transaction transaction);

    void updateAmount(long billId, long amount);
}
