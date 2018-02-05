package main.dao;

import main.dao.mapper.UserMapper;
import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }


    @Override
    public Optional<User> findByName(String name) {

        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM bank.users WHERE name = ?",
                new Object[]{name}, new UserMapper()
        ));
    }

    @Override
    public long create(User user) {
        simpleJdbcInsert
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("password", user.getPassword());
        parameters.put("description", user.getDescription());
        parameters.put("address", user.getAddress());
        parameters.put("age", user.getAge());
        parameters.put("email", user.getEmail());
        Number key = simpleJdbcInsert.executeAndReturnKey(parameters);
        return key.longValue();
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("UPDATE bank.users SET " +
                "name = ?, password = ?, description = ?, address = ?, age = ?, email = ?",
                user.getName(),
                user.getPassword(),
                user.getDescription(),
                user.getAddress(),
                user.getAge(),
                user.getEmail());
    }

    @Override
    public boolean exists(Long id) {
        Integer amount = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM bank.users WHERE id = ?", Integer.class, id);
        return amount != null && amount > 0;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM bank.users", User.class);
    }

    @Override
    public long count() {
        return jdbcTemplate.queryForObject(
                "SELECT count(*) FROM bank.users", Integer.class);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM bank.users WHERE id = ?", id);
    }

    @Override
    public void delete(String name) {
        jdbcTemplate.update("DELETE FROM bank.users WHERE name = ?", name);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM bank.users");
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM bank.users WHERE name = ?",
                new Object[]{id}, new UserMapper()
        ));
    }
}
