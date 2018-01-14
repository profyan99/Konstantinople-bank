package main.dao;

import main.entity.Bill;
import main.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByName(String name);


    List<Bill> findAllBillsById(long id);
    /*void save(User user);

    List<User> findAll();

    User getById(int id);

    User getByName(String name);

    void update(User user);

    void delete(User user);*/

}
