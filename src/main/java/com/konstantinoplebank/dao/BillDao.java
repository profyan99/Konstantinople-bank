package com.konstantinoplebank.dao;

import com.konstantinoplebank.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillDao {
    Optional<Bill> findBillById(long id);

    List<Bill> findBillsByUserId(long id);

    void createBill(Bill bill);

    void updateBill(Bill bill);
}
