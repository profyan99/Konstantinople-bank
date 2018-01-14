package main.service;

import main.dao.UserDao;
import main.dao.UserDaoImpl;
import main.entity.Bill;
import main.entity.Transaction;
import main.entity.User;
import main.response.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("userService")
public class UserServiceImpl implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws Exception {
        userDao.save(user);
    }

    @Override
    public List<UserProfile> findAll() {
        List<UserProfile> list = new ArrayList<>();
        userDao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public User getById(long id) {
        return userDao.findOne(id);
    }

    @Override
    public User getByName(String name) {
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
        User user = new User(userProfile.getName(), userProfile.getEmail(), userProfile.getPassword(),
                userProfile.getDescription(), userProfile.getAddress(), userProfile.getAge());
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
