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

    private int amount;

    Date date;

    private String description;

}
