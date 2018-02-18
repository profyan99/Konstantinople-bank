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

    private Set<Transaction> transactions = new HashSet<>();

    private long amount;

}
