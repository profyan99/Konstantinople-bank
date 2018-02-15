package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Bill;

import java.util.List;

public interface BillMapper {
    Bill findBillById(long id);

    List<Bill> findBillsByUserId(long id);

    void createBill(Bill bill);

    void updateBill(Bill bill);
}
