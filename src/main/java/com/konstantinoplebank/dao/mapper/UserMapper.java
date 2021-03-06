package com.konstantinoplebank.dao.mapper;


import com.konstantinoplebank.entity.User;

import java.util.List;

public interface UserMapper {

    User findByName(String name);

    void save(User user);

    long create(User user);

    boolean existsById(Long id);

    boolean existsByName(String name);

    List<User> findAll();

    long count();

    void deleteById(Long id);

    void deleteByName(String name);

    void deleteAll();

    User findById(long id);
}
