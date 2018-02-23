package com.konstantinoplebank.dao;

import com.konstantinoplebank.entity.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDao {

    /**
     * Find {@link Bill} by bill ID
     * @param id - id of the bill
     * @return {@link Optional<Bill>}
     */
    Optional<Bill> findBillById(long id, long userId);

    /**
     * Find {@link Bill} by {@link com.konstantinoplebank.entity.User} ID
     * @return empty {@link List} if bills didn't find
     * else return bills
     */
    List<Bill> findBillsByUserId(long id);

    /**
     * Find {@link Bill} by {@link com.konstantinoplebank.entity.User} name
     * @return empty {@link List} if bills didn't find
     * else return bills
     */
    List<Bill> findBillsByUserName(String name);

    /**
     * Find all {@link Bill} in DataBase
     * @return empty {@link List} if bills didn't find
     * else return bills
     */
    List<Bill> findAll();

    /**
     * Creates new {@link Bill}
     * @return Bill with setted out ID, if create was successful
     * else return bill from params
     */
    Bill createBill(Bill bill);

    /**
     * Update amount money in {@link Bill}
     * @param billId - id of the bill
     * @param amount - new amount of money in current bill
     */
    void updateAmount(long billId, long amount);
}
