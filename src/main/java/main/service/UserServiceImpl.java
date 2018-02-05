package main.service;

import main.dao.UserDao;
import main.entity.Bill;
import main.entity.Role;
import main.entity.Transaction;
import main.entity.User;
import main.response.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

;

/**
 * Implementation of {@link UserService} interface
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserDao userDao;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
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
        userDao.findAll().forEach(list::add);
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
        userDao.delete(user);
    }

    @Override
    public long create(UserProfile userProfile) {
        User user = new User(
                userProfile.getName(),
                userProfile.getEmail(),
                bCryptPasswordEncoder.encode(userProfile.getPassword()),
                userProfile.getDescription(),
                userProfile.getAddress(),
                userProfile.getAge(),
                new HashSet<>(Collections.singletonList(Role.USER)));

        userDao.save(user);
        return user.getId();
    }
    @Override
    public Set<Bill> getUserBills(long id) {
        return userDao.findOne(id).getBills();
    }

    @Override
    public Set<Transaction> getUserTransactions(long id) {
        return userDao.findOne(id).getTransactions();
    }

    @Override
    public UserProfile getUserProfile(long id) {
        return userDao.findOne(id);
    }
}
