package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    Bill findBillById(long id);

    List<Bill> findBillsByUserId(long id);

    void createBill(Bill bill);

    //TODO i need update bill?
    void updateBill(Bill bill);

    void updateAmount(@Param("billId") long billId, @Param("amount") long amount);
}
