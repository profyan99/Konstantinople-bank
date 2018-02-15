package com.konstantinoplebank.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum that represents role of {@link User}.
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
