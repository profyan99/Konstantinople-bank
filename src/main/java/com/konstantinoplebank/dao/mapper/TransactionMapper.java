package com.konstantinoplebank.dao.mapper;

import com.konstantinoplebank.entity.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionMapper {
    Transaction findTransactionById(long id);

    List<Transaction> findTransactionsByUserId(long id);

    void createTransaction(Transaction transaction);
}
