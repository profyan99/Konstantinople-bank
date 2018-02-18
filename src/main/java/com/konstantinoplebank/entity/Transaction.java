package com.konstantinoplebank.entity;

import lombok.*;

import javax.persistence.*;


import java.util.Date;

/**
 * Simple JavaBean domain object that represents
 * transactions of {@link User} and User's {@link Bill}.
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private long id;

    private User user;

    private Bill bill;

    private long amount;

    Date date;

    private String description;

    public Transaction(User user, Bill bill, long amount, Date date, String description) {
        this.user = user;
        this.bill = bill;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
}
