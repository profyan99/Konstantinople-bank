package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.entity.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for {@link User}
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

public interface UserService {
    long save(User user) throws Exception;

    List<UserProfile> findAll();

    Optional<User> getById(long id);

    Optional<User> getByName(String name);

    void update(User user);

    boolean existsById(Long id);

    boolean existsByName(String name);

    void delete(User user);

    long create(UserProfile userProfile);

    Set<Bill> getUserBills(long id);

    Set<Transaction> getUserTransactions(long id);

    UserProfile getUserProfile(long id);
}
