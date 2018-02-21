package com.konstantinoplebank.dao.implementation;

import com.konstantinoplebank.dao.UserDao;
import com.konstantinoplebank.dao.mapper.RoleMapper;
import com.konstantinoplebank.dao.mapper.UserMapper;
import com.konstantinoplebank.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDaoImpl implements UserDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SqlSessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(@Qualifier("SimpleSqlFactory") SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> user = Optional.empty();
        try (SqlSession session = sessionFactory.openSession()) {
            user = Optional.of(
                    session
                            .getMapper(UserMapper.class)
                            .findByName(name)
            );
            return user;
        } catch (RuntimeException e) {
            logger.error("Couldn't find by name user: " + e.toString());
            return user;
        }
    }

    @Override
    public void update(User user) {
        SqlSession session = sessionFactory.openSession();
        try {
            session.getMapper(UserMapper.class).save(user);
            session.getMapper(RoleMapper.class).updateRole(new ArrayList<>(user.getRoles()), user.getId());
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't update user: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public User create(User user) {
        SqlSession session = sessionFactory.openSession();
        try {
            session.getMapper(UserMapper.class).create(user);
            session.getMapper(RoleMapper.class).createRole(new ArrayList<>(user.getRoles()), user.getId());
            session.commit();
            return user;
        } catch(RuntimeException e) {
            logger.error("Couldn't create user: "+e.toString());
            session.rollback();
            return user;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean existsById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).existsById(id);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist user: " + e.toString());
            return false;
        }
    }

    @Override
    public boolean existsByName(String name) {
        try(SqlSession session = sessionFactory.openSession()) {
            return session.getMapper(UserMapper.class).existsByName(name);
        } catch (RuntimeException e) {
            logger.error("Couldn't check for exist user: "+e.toString());
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> list = Collections.emptyList();
        try (SqlSession session = sessionFactory.openSession()) {
            list = session.getMapper(UserMapper.class).findAll();
            return list;
        } catch (RuntimeException e) {
            logger.error("Couldn't findall users: " + e.toString());
            return list;
        }

    }

    @Override
    public long count() {
        long count = 0;
        try(SqlSession session = sessionFactory.openSession()) {
            count = session.getMapper(UserMapper.class).count();
        } catch (RuntimeException e) {
            logger.error("Couldn't count users: "+e.toString());
        }
        return count;
    }


    @Override
    public void deleteAll() {
        SqlSession session = sessionFactory.openSession();
        try {
            session.getMapper(UserMapper.class).deleteAll();
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete all users: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void deleteById(Long id) {
        SqlSession session = sessionFactory.openSession();
        try {
            session.getMapper(UserMapper.class).deleteById(id);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete by id: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteByName(String name) {
        SqlSession session = sessionFactory.openSession();
        try {
            session.getMapper(UserMapper.class).deleteByName(name);
            session.commit();
        } catch (RuntimeException e) {
            logger.error("Couldn't delete by name: "+e.toString());
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> user = Optional.empty();
        try (SqlSession session = sessionFactory.openSession()) {
            user = Optional.of(
                    session
                            .getMapper(UserMapper.class)
                            .findById(id)
            );
            return user;
        } catch (RuntimeException e) {
            logger.error("Couldn't find by id user: " + e.toString());
            return user;
        }
    }
}
