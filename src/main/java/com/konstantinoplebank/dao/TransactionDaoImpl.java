package com.konstantinoplebank.dao;

import com.konstantinoplebank.dao.mapper.TransactionMapper;
import com.konstantinoplebank.dao.mapper.UserMapper;
import com.konstantinoplebank.entity.Transaction;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Optional;

public class TransactionDaoImpl extends SqlSessionDaoSupport implements TransactionDao {

    @Override
    public Optional<Transaction> findTransactionById(long id) {
        try (SqlSession session = getSqlSession()) {
            return Optional.of(session
                    .getMapper(TransactionMapper.class)
                    .findTransactionById(id));
        }
    }

    @Override
    public List<Transaction> findTransactionsByUserId(long id) {
        try (SqlSession session = getSqlSession()) {
            return session
                    .getMapper(TransactionMapper.class)
                    .findTransactionsByUserId(id);
        }
    }

    @Override
    public void createTransaction(Transaction transaction) {
        try (SqlSession session = getSqlSession()) {
            session
                    .getMapper(TransactionMapper.class)
                    .createTransaction(transaction);
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        try (SqlSession session = getSqlSession()) {
            session
                    .getMapper(TransactionMapper.class)
                    .updateTransaction(transaction);
        }
    }
}
