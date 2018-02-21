package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    Bill findBillById(long id);

    List<Bill> findBillsByUserId(long id);

    List<Bill> findBillsByUserName(String name);

    void createBill(Bill bill);

    void updateAmount(@Param("billId") long billId, @Param("amount") long amount);
}
