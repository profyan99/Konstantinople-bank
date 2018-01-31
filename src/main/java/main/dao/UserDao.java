package main.dao;

import main.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    Optional<User> findByName(String name);

    Optional<User> findById(long id);


}
