package com.konstantinoplebank.dao;

import com.konstantinoplebank.entity.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDao {
    Optional<Bill> findBillById(long id);

    List<Bill> findBillsByUserId(long id);

    List<Bill> findBillsByUserName(String name);

    List<Bill> findAll();

    void createBill(Bill bill);

    void updateAmount(long billId, long amount);
}
