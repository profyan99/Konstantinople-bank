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
    List<UserProfile> findAll();

    Optional<User> getById(long id);

    Optional<User> getByName(String name);

    void update(UserProfile user);

    void createBill(long userId, long amount);

    Optional<Bill> getBill(long billiId);

    void createTransaction(long amount, String description, long billId, long userId);

    Optional<Transaction> getTransaction(long trId);

    boolean existsById(Long id);

    boolean existsByName(String name);

    void delete(User user);

    long create(UserProfile userProfile);

    Set<Bill> getUserBills(long id);

    Set<Transaction> getUserTransactions(long id);

    UserProfile getUserProfile(long id);
}
