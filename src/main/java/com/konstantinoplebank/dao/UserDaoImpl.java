package com.konstantinoplebank.dao;

import com.konstantinoplebank.dao.mapper.UserMapper;
import com.konstantinoplebank.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    @Override
    public Optional<User> findByName(String name) {
        try (SqlSession session = getSqlSession()) {
            return Optional.of(
                    session
                            .getMapper(UserMapper.class)
                            .findByName(name)
            );
        }
    }

    @Override
    public void save(User user) {
        try (SqlSession session = getSqlSession()) {
            session.getMapper(UserMapper.class).save(user);
        }
    }

    @Override
    public long create(User user) {
        try (SqlSession session = getSqlSession()) {
            session.getMapper(UserMapper.class).create(user);
            return user.getId();
        }
    }

    @Override
    public boolean exists(Long id) {
        try (SqlSession session = getSqlSession()) {
            return session.getMapper(UserMapper.class).exists(id);
        }
    }

    @Override
    public List<User> findAll() {
        try (SqlSession session = getSqlSession()) {
            return session.getMapper(UserMapper.class).findAll();
        }
    }

    @Override
    public long count() {
        try (SqlSession session = getSqlSession()) {
            return session.getMapper(UserMapper.class).count();
        }
    }


    @Override
    public void deleteAll() {
        try (SqlSession session = getSqlSession()) {
            session.getMapper(UserMapper.class).deleteAll();
        }

    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = getSqlSession()) {
            session.getMapper(UserMapper.class).deleteById(id);
        }
    }

    @Override
    public void deleteByName(String name) {
        try (SqlSession session = getSqlSession()) {
            session.getMapper(UserMapper.class).deleteByName(name);
        }
    }

    @Override
    public Optional<User> findById(long id) {
        try (SqlSession session = getSqlSession()) {
            return Optional.of(
                    session
                            .getMapper(UserMapper.class)
                            .findById(id)
            );
        }
    }
}
