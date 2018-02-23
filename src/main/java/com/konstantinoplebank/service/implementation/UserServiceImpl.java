package com.konstantinoplebank.service.implementation;

import com.konstantinoplebank.dao.UserDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Role;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.response.UserBill;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.response.UserTransaction;
import com.konstantinoplebank.service.BillService;
import com.konstantinoplebank.service.TransactionService;
import com.konstantinoplebank.service.UserService;
import com.konstantinoplebank.utils.exception.InvalidTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        List<UserProfile> users = new ArrayList<>();
        userDao.findAll().forEach((user -> users.add(new UserProfile(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getDescription(),
                user.getAddress(),
                user.getAge(),
                user.getRoles()
        ))));
        return users;
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
    public UserProfile create(UserProfile userProfile) {
        User user = new User(
                userProfile.getName(),
                userProfile.getEmail(),
                passwordEncoder.encode(userProfile.getPassword()),
                userProfile.getDescription(),
                userProfile.getAddress(),
                userProfile.getAge(),
                new HashSet<>(Collections.singletonList(Role.USER)));

        userDao.create(user);
        return new UserProfile(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getDescription(),
                user.getAddress(), user.getAge(), user.getRoles());
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
    public Optional<UserProfile> getUserProfile(long id) {
        User user = userDao.findById(id).orElseThrow(() -> new UsernameNotFoundException("id: "+id));
        return Optional.of(new UserProfile()
                {{
                    setId(user.getId());
                    setAddress(user.getAddress());
                    setAge(user.getAge());
                    setDescription(user.getDescription());
                    setEmail(user.getEmail());
                    setName(user.getName());
                    setRoles(user.getRoles());
                    setPassword(user.getPassword());
                }});
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
    public UserBill createBill(long userId, long amount) {
        Bill bill = billService.create(userId, amount);
        if(bill.getId() == 0) {
            return null;
        }
        else {
            return new UserBill(bill.getId(), bill.getTransactions(), bill.getAmount());
        }
    }

    @Override
    public Optional<Bill> getBill(long billiId, long userId) {
        return billService.findById(billiId, userId);
    }

    @Override
    public UserTransaction createTransaction(long amount, String description, long billId, long userId) {
        try {
            Transaction transaction = transactionService.create(userId, billId, amount, description);
            return new UserTransaction(transaction.getId(), transaction.getUser().getId(), transaction.getBill().getId(),
                    transaction.getAmount(), transaction.getDate(), transaction.getDescription());
        } catch (InvalidTransaction e) {
            return null;
        }

    }

    @Override
    public Optional<Transaction> getTransaction(long trId, long billId, long userId) {
        return transactionService.findById(trId, billId, userId);
    }
}
