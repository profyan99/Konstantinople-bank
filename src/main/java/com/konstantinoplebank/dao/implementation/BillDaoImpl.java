package com.konstantinoplebank.dao.implementation;

import com.konstantinoplebank.dao.BillDao;
import com.konstantinoplebank.dao.mapper.BillMapper;
import com.konstantinoplebank.entity.Bill;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class BillDaoImpl implements BillDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SqlSessionFactory sessionFactory;

    @Autowired
    public BillDaoImpl(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Bill> findBillById(long id) {
        Optional<Bill> bill = Optional.empty();
        try (SqlSession session = sessionFactory.openSession()) {
            bill = Optional.of(session
                    .getMapper(BillMapper.class)
                    .findBillById(id));
            return bill;
        } catch (RuntimeException e) {
            logger.error("Couldn't findById: " + e.toString());
            return bill;
        }
    }

    @Override
    public List<Bill> findBillsByUserId(long id) {
        List<Bill> bill = Collections.emptyList();
        try (SqlSession session = sessionFactory.openSession()) {
            bill = session
                    .getMapper(BillMapper.class)
                    .findBillsByUserId(id);
            return bill;
        } catch (RuntimeException e) {
            logger.error("Couldn't findByUserId: " + e.toString());
            return bill;
        }
    }

    @Override
    public void createBill(Bill bill) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(BillMapper.class)
                    .createBill(bill);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't create bill: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateAmount(long billId, long amount) {
        SqlSession session = sessionFactory.openSession();
        try {
            session
                    .getMapper(BillMapper.class)
                    .updateAmount(billId, amount);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't update bill amount: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Bill> findBillsByUserName(String name) {
        List<Bill> bill = Collections.emptyList();
        try (SqlSession session = sessionFactory.openSession()) {
            bill = session
                    .getMapper(BillMapper.class)
                    .findBillsByUserName(name);
            return bill;
        } catch (RuntimeException e) {
            logger.error("Couldn't findByUserName: " + e.toString());
            return bill;
        }
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> bill = Collections.emptyList();
        try (SqlSession session = sessionFactory.openSession()) {
            bill = session
                    .getMapper(BillMapper.class)
                    .findAll();
            return bill;
        } catch (RuntimeException e) {
            logger.error("Couldn't find all bills: " + e.toString());
            return bill;
        }
    }
}
