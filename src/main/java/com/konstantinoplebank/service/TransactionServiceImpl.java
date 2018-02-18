package com.konstantinoplebank.service;

import com.konstantinoplebank.dao.BillDao;
import com.konstantinoplebank.dao.TransactionDao;
import com.konstantinoplebank.dao.UserDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.utils.exception.BillNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        //transactionDao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Transaction getTransactionById(long id) {
        return transactionDao.findTransactionById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserName(String name) {
        return null;//transactionDao.findByUserName(name);
    }

    @Override
    @Transactional
    public void createTransaction(long userid, long billid, long amount, String description) {
        Bill bill = billService.findBillById(billid).orElseThrow(() -> new BillNotFoundException());
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
    }
}
