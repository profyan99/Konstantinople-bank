package com.konstantinoplebank.service;

import com.konstantinoplebank.dao.BillDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
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
    public Optional<Bill> findBillById(long id) {
        return billDao.findBillById(id);
    }

    @Override
    public List<Bill> findBillsByUserId(long id) {
        return billDao.findBillsByUserId(id);
    }

    @Override
    public long createBill(long userid, long amount) {
        User user = userService.getById(userid).orElseThrow(() -> new UsernameNotFoundException("id "+userid));
        Bill bill = new Bill(user, amount);
        billDao.createBill(bill);
        //user.getBills().add(bill);
        return bill.getId();
    }

    @Override
    public void applyTransaction(Transaction transaction) {
        Bill currBill = transaction.getBill();
        if(currBill.getAmount() + transaction.getAmount() >= 0) {
            currBill.setAmount(currBill.getAmount() + transaction.getAmount());
           // currBill.getTransactions().add(transaction);
            billDao.updateAmount(currBill.getId(), currBill.getAmount());
            //TODO transaction create without bill update or only bill update?
        }
        else {
            throw new InvalidTransaction("Less than zero");
        }
    }

    @Override
    public void updateAmount(long billId, long amount) {
        billDao.updateAmount(billId, amount);
    }
}
