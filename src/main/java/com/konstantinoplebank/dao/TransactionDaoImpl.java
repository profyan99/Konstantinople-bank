package com.konstantinoplebank.dao;

import com.konstantinoplebank.dao.mapper.TransactionMapper;
import com.konstantinoplebank.dao.mapper.UserMapper;
import com.konstantinoplebank.entity.Transaction;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SqlSessionFactory sessionFactory;

    @Autowired
    public TransactionDaoImpl(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Transaction> findTransactionById(long id) {
        Optional<Transaction> transaction = Optional.empty();
        try (SqlSession session = sessionFactory.openSession()) {
            transaction = Optional.of(session
                    .getMapper(TransactionMapper.class)
                    .findTransactionById(id));
            return transaction;
        } catch (RuntimeException e) {
            logger.error("Couldn't findTransactionById: " + e.toString());
            return transaction;
        }
    }

    @Override
    public List<Transaction> findTransactionsByUserId(long id) {
        List<Transaction> transactions = Collections.emptyList();
        try (SqlSession session = sessionFactory.openSession()) {
            transactions = session
                    .getMapper(TransactionMapper.class)
                    .findTransactionsByUserId(id);
            return transactions;
        } catch (RuntimeException e) {
            logger.error("Couldn't findTransactionsByUserId: " + e.toString());
            return transactions;
        }
    }

    @Override
    public void createTransaction(Transaction transaction) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(TransactionMapper.class)
                    .createTransaction(transaction);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't createTransaction: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }
    }
}
