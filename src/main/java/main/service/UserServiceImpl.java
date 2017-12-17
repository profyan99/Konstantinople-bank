package main.service;

import main.dao.UserDao;
import main.dao.UserDaoImpl;
import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    private final
    UserDaoImpl userDao;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) throws Exception {
        userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        list.addAll((userDao.findAll()));
       // userDao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public User getById(long id) {
        return userDao.findById(id);
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
    public long create(User user) {
        userDao.create(user);
        return user.getId();
    }
}
