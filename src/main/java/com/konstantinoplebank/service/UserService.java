package com.konstantinoplebank.service;

import com.konstantinoplebank.entity.Bill;
import com.konstantinoplebank.entity.Transaction;
import com.konstantinoplebank.entity.User;
import com.konstantinoplebank.response.UserBill;
import com.konstantinoplebank.response.UserProfile;
import com.konstantinoplebank.response.UserTransaction;

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
    List<UserProfile> findAll();

    Optional<User> getById(long id);

    Optional<User> getByName(String name);

    void update(UserProfile user);

    UserBill createBill(long userId, long amount);

    Optional<Bill> getBill(long id, long userId);

    UserTransaction createTransaction(long amount, String description, long billId, long userId);

    Optional<Transaction> getTransaction(long trId, long billId, long userId);

    boolean existsById(Long id);

    boolean existsByName(String name);

    void delete(User user);

    UserProfile create(UserProfile userProfile);

    Set<Bill> getUserBills(long id);

    Set<Transaction> getUserTransactions(long id);

    Optional<UserProfile> getUserProfile(long id);
}
