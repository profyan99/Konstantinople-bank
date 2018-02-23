package com.konstantinoplebank.service.implementation;

import com.konstantinoplebank.dao.TransactionDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.service.TransactionService;
import com.konstantinoplebank.utils.exception.BillNotFoundException;
import com.konstantinoplebank.utils.exception.InvalidTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Implementation of {@link TransactionService} interface
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BillService billService;

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao, BillService billService) {
        this.transactionDao = transactionDao;
        this.billService = billService;
    }

    @Override
    public Optional<Transaction> findById(long id, long billId, long userId) {
        return transactionDao.findTransactionById(id, billId, userId);
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
    public Transaction create(long userId, long billId, long amount, String description) throws InvalidTransaction {
        Bill bill = billService.findById(billId, userId).orElseThrow(BillNotFoundException::new);
        Transaction transaction =
                new Transaction(
                        bill.getUser(),
                        bill,
                        amount,
                        new Date(),
                        description
                );
        try {
            billService.applyTransaction(transaction);
            transactionDao.createTransaction(transaction);
            return transaction;
        } catch (InvalidTransaction i) {
            logger.error("Could not create Transaction: "+i.toString());
            throw i;
        }
        // TODO maybe rewrite this method without try\catch or this Spring Transactional
    }

    @Override
    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }
}
