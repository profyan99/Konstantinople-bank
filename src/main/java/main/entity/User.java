package main.entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String name;

    private String email;

    private String password;

    private String description;

    public User(int id, String name, String email, String password, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }
    public User() { }


    public User(long id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }


    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "User Profile #"+
                id+"\n"+"Name: "+name+"\nPass: "+
                password+"\nEmail: "+email+"\nDesc: "+description;
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
}
