package main.dao;

import main.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDao{

    Optional<User> findByName(String name);

    void save(User user);

    long create(User user);

    boolean exists(Long id);

    List<User> findAll();

    long count();

    void delete(Long id);

    void delete(String name);

    void deleteAll();

    Optional<User> findById(long id);

}
