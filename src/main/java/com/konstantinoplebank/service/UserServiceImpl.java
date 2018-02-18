package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.dao.UserDao;
import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Role;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.response.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long save(User user) throws Exception {
        userDao.save(user);
        return user.getId();
    }

    @Override
    public List<UserProfile> findAll() {
        List<UserProfile> list = new ArrayList<>();
        list.addAll(userDao.findAll());
        return list;
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
    public void update(User user) {
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
                new BCryptPasswordEncoder().encode(userProfile.getPassword()),
                userProfile.getDescription(),
                userProfile.getAddress(),
                userProfile.getAge(),
                new HashSet<>(Collections.singletonList(Role.USER)));

        userDao.create(user);
        return user.getId();
    }
    @Override
    public Set<Bill> getUserBills(long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id: "+id))
                .getBills();
    }

    @Override
    public Set<Transaction> getUserTransactions(long id) {
        return /*userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id: "+id))
                .getTransactions();*/ null;
    }

    @Override
    public UserProfile getUserProfile(long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("id: "+id));
    }
}
