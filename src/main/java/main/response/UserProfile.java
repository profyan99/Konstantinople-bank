package main.response;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * Simple embeddable JavaBean object that uses as response to a queries
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@EqualsAndHashCode
@Embeddable
@ToString
public class UserProfile {

    private String name;

    private String email;

    private String password;

    private String description;

    private String address;

    private int age;

    public UserProfile() {

    }

    public UserProfile(String name, String email, String password, String description, String address, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
