package main.entity;

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

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bill")
    private Bill bill;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    @Temporal(value = TemporalType.DATE)
    Date date;

    @Column(name = "description")
    private String description;

}
