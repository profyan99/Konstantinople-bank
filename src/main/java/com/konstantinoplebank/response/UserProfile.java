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

    private String name;
    private String email;
    private String password;
    private String description;
    private String address;
    private int age;
    private Set<Role> roles;

}
