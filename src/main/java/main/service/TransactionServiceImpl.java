package main.service;

import main.dao.TransactionDao;
import main.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    public TransactionServiceImpl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        transactionDao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Transaction getTransactionById(long id) {
        return transactionDao.findOne(id);
    }

    @Override
    public List<Transaction> getAllTransactionsByUserName(String name) {
        return transactionDao.findByUserName(name);
    }
}
