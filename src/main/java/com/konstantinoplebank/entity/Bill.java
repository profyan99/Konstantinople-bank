package com.konstantinoplebank.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents bill of {@link User}.
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private long id;

    private User user;

    private Set<Transaction> transactions = new HashSet<>();

    private long amount;
}
