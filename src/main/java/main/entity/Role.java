package main.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum that represents role of {@link User}.
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
