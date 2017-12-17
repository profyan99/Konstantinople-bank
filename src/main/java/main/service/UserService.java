package main.service;

import main.entity.User;

import java.util.List;

public interface UserService {
    void save(User user) throws Exception;

    List<User> findAll();

    User getById(long id);

    User getByName(String name);

    void update(User user);

    void delete(User user);

    long create(User user);
}
