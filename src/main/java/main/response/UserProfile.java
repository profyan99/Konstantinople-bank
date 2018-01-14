package main.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class UserProfile {
    @NonNull
    protected String name;

    protected String email;

    @NonNull
    protected String password;

    protected String description;

    protected String address;

    protected int age;
}
