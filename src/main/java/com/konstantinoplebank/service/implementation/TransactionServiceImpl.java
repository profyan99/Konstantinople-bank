package com.konstantinoplebank.service.implementation;

import com.konstantinoplebank.dao.TransactionDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.service.TransactionService;
import com.konstantinoplebank.utils.exception.BillNotFoundException;
import com.konstantinoplebank.utils.exception.TransactionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Implementation of {@link TransactionService} interface
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;


    private final BillService billService;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao, BillService billService) {
        this.transactionDao = transactionDao;
        this.billService = billService;
    }

    @Override
    public Transaction findById(long id) {
        return transactionDao.findTransactionById(id).orElseThrow(TransactionNotFoundException::new);
    }

    @Override
    public List<Transaction> findByUserName(String name) {
        return transactionDao.findTransactionsByUserName(name);
    }

    @Override
    public List<Transaction> findByUserId(long id) {
        return transactionDao.findTransactionsByUserId(id);
    }

    @Override
    public List<Transaction> findByBillId(long id) {
        return transactionDao.findTransactionsByBillId(id);
    }

    @Override
    public long create(long userId, long billId, long amount, String description) {
        Bill bill = billService.findById(billId).orElseThrow(BillNotFoundException::new);
        Transaction transaction =
                new Transaction(
                        bill.getUser(),
                        bill,
                        amount,
                        new Date(),
                        description
                );
        transactionDao.createTransaction(transaction);
        billService.applyTransaction(transaction);
        return transaction.getId();
    }
}
