package com.example.bitsbids3.service;

import com.example.bitsbids3.entity.User;
import com.example.bitsbids3.userdao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService /*implements org.springframework.security.core.userdetails.UserDetailsService*/ {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Userdao userdao;

    public UserService(Userdao userdao) {
        this.userdao = userdao;
    }

    public User addUser(User user) {
        return this.userdao.save(user);
    }
    public boolean isUsernameExists(String username) {
        return userdao.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return userdao.existsByEmail(email);
    }


    
    public boolean isValidUser(String username, String password) {
        User user = userdao.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }



/*
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userdao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities() // you may need to adjust this based on your role configuration
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // add roles based on your user entity
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // add more roles as needed

        return authorities;
    }*/
}
