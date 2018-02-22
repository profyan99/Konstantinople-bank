package com.konstantinoplebank.service.implementation;

import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.dao.UserDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Role;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.service.TransactionService;
import com.konstantinoplebank.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Implementation of {@link UserService} interface
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserDao userDao;
    private final BillService billService;
    private final TransactionService transactionService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BillService billService, TransactionService transactionService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.billService = billService;
        this.transactionService = transactionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserProfile> findAll() {
        return new ArrayList<>(userDao.findAll());
    }

    @Override
    public  Optional<User> getById(long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> getByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void update(UserProfile user) {
        userDao.update(new User(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getDescription(),
                user.getAddress(),
                user.getAge(),
                user.getRoles()));
    }

    @Override
    public void delete(User user) {
        userDao.deleteById(user.getId());
    }

    @Override
    public long create(UserProfile userProfile) {
        User user = new User(
                userProfile.getName(),
                userProfile.getEmail(),
                passwordEncoder.encode(userProfile.getPassword()),
                userProfile.getDescription(),
                userProfile.getAddress(),
                userProfile.getAge(),
                new HashSet<>(Collections.singletonList(Role.USER)));

        userDao.create(user);
        return user.getId();
    }
    @Override
    public Set<Bill> getUserBills(long id) {
        return new HashSet<>(billService.findByUserId(id));
    }

    @Override
    public Set<Transaction> getUserTransactions(long id) {
        return new HashSet<>(transactionService.findByUserId(id));
    }

    @Override
    public UserProfile getUserProfile(long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id: "+id));
    }

    @Override
    public boolean existsById(Long id) {
        return userDao.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return userDao.existsByName(name);
    }

    @Override
    public void createBill(long userId, long amount) {
        billService.create(userId, amount);
    }

    @Override
    public Optional<Bill> getBill(long billiId) {
        return billService.findById(billiId);
    }

    @Override
    public void createTransaction(long amount, String description, long billId, long userId) {
        transactionService.create(userId, billId, amount, description);
    }

    @Override
    public Optional<Transaction> getTransaction(long trId) {
        return transactionService.findById(trId);
    }
}
