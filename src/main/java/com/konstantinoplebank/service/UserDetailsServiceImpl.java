package com.konstantinoplebank.service;

import com.konstantinoplebank.dao.UserDao;
import com.konstantinoplebank.entity.Role;
import com.konstantinoplebank.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserDetailsService} interface
 *
 * @author Konstantin Artushkevich
 * @version 1.0
 */

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userDao.findByName(s).orElseThrow(() -> new UsernameNotFoundException(s));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), grantedAuthorities);
    }
}
