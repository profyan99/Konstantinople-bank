package com.konstantinoplebank.dao;

import com.konstantinoplebank.dao.mapper.BillMapper;
import com.konstantinoplebank.entity.Bill;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Optional;

public class BillDaoImpl extends SqlSessionDaoSupport implements BillDao {
    @Override
    public Optional<Bill> findBillById(long id) {
        try (SqlSession session = getSqlSession()) {
            return Optional.of(session
                    .getMapper(BillMapper.class)
                    .findBillById(id));
        }
    }

    @Override
    public List<Bill> findBillsByUserId(long id) {
        try (SqlSession session = getSqlSession()) {
            return session
                    .getMapper(BillMapper.class)
                    .findBillsByUserId(id);
        }
    }

    @Override
    public void createBill(Bill bill) {
        try (SqlSession session = getSqlSession()) {
            session
                    .getMapper(BillMapper.class)
                    .createBill(bill);
        }
    }

    @Override
    public void updateBill(Bill bill) {
        try (SqlSession session = getSqlSession()) {
            session
                    .getMapper(BillMapper.class)
                    .updateBill(bill);
        }
    }
}
