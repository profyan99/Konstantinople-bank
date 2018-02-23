package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionMapper {
    Transaction findTransactionById(@Param("id") long id, @Param("billId") long billId, @Param("userId") long userId);

    List<Transaction> findTransactionsByUserId(long id);

    void createTransaction(Transaction transaction);

    List<Transaction> findTransactionsByBillId(long id);

    List<Transaction> findTransactionsByUserName(String name);

    List<Transaction> findAll();
}
