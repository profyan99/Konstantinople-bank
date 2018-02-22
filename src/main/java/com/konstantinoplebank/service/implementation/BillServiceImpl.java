package com.konstantinoplebank.service.implementation;

import com.konstantinoplebank.dao.BillDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.service.UserService;
import com.konstantinoplebank.utils.exception.InvalidTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("billService")
public class BillServiceImpl implements BillService {

    private final BillDao billDao;

    private final UserService userService;

    @Autowired
    public BillServiceImpl(BillDao billDao, UserService userService) {
        this.billDao = billDao;
        this.userService = userService;
    }

    @Override
    public Optional<Bill> findById(long id) {
        return billDao.findBillById(id);
    }

    @Override
    public List<Bill> findByUserId(long id) {
        return billDao.findBillsByUserId(id);
    }

    @Override
    public long create(long userid, long amount) {
        User user = userService.getById(userid).orElseThrow(() -> new UsernameNotFoundException("id "+userid));
        Bill bill = new Bill(user, amount);
        billDao.createBill(bill);
        return bill.getId();
    }

    @Override
    public void applyTransaction(Transaction transaction) {
        Bill currBill = transaction.getBill();
        if(currBill.getAmount() + transaction.getAmount() >= 0) {
            currBill.setAmount(currBill.getAmount() + transaction.getAmount());
            billDao.updateAmount(currBill.getId(), currBill.getAmount());
        }
        else {
            throw new InvalidTransaction("Less than zero");
        }
    }

    @Override
    public List<Bill> findByUserName(String name) {
        return billDao.findBillsByUserName(name);
    }

    @Override
    public void updateAmount(long billId, long amount) {
        billDao.updateAmount(billId, amount);
    }

    @Override
    public List<Bill> findAll() {
        return billDao.findAll();
    }
}
