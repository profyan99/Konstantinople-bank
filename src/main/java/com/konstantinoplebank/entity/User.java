package com.konstantinoplebank.entity;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple JavaBean domain object that represents a User
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    private String name;
    private String email;
    private String password;
    private String description;
    private String address;
    private int age;
    private Set<Bill> bills;
    private Set<Role> roles;
    private String confirmPassword;

    public User(String name, String email, String password, String description, String address, int age,
                Set<Role> roles) {

        this(0, name, email, password, description, address, age, new HashSet<>(), roles,"");
    }

    public User(String name, String email, String password, String description, String address, int age, long id) {
        this(id, name, email, password, description, address, age, new HashSet<>(), new HashSet<>(),"");
    }
}
