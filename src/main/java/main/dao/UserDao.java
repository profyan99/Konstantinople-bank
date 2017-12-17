package main.dao;

import main.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByName(String name);
    /*void save(User user);

    List<User> findAll();

    User getById(int id);

    User getByName(String name);

    void update(User user);

    void delete(User user);*/

}
