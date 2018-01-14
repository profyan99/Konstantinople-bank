package main.entity;

import lombok.*;

import javax.persistence.*;

import org.springframework.data.annotation.Id;

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
    private User user;

    @ManyToOne
    private Bill bill;

    private int amount;

    private String description;

}
