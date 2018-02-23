package com.konstantinoplebank.response;

import com.konstantinoplebank.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserBill {
    private long id;

    private long userId;

    private Set<Transaction> transactions = new HashSet<>();

    private long amount;

    public UserBill(long userId, Set<Transaction> transactions, long amount) {
        this(0, userId, transactions, amount);
    }
}
