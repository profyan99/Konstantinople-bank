package com.konstantinoplebank.response;

import com.konstantinoplebank.entity.Role;
import lombok.*;

import java.util.Set;


/**
 * Simple embeddable JavaBean object that uses as response to a queries
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private long id;
    private String name;
    private String email;
    private String password;
    private String description;
    private String address;
    private int age;
    private Set<Role> roles;

    public UserProfile(String name, String email, String password, String description, String address, int age, Set<Role> roles) {
        this(0, name, email, password, description, address, age, roles);
    }
}
