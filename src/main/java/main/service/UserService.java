package main.service;

import main.entity.Bill;
import main.entity.Transaction;
import main.entity.User;
import main.response.UserProfile;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User user) throws Exception;

    List<? extends UserProfile> findAll();

    User getById(long id);

    User getByName(String name);

    void update(User user);

    void delete(User user);

    long create(UserProfile userProfile);

    Set<Bill> getUserBills(long id);

    Set<Transaction> getUserTransactions(long id);

    UserProfile getUserProfile(long id);
}
