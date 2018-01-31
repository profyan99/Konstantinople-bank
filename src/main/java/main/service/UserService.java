package main.service;

import main.entity.Bill;
import main.entity.Transaction;
import main.entity.User;
import main.response.UserProfile;

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

    void delete(User user);

    long create(UserProfile userProfile);

    Set<Bill> getUserBills(long id);

    Set<Transaction> getUserTransactions(long id);

    UserProfile getUserProfile(long id);
}
