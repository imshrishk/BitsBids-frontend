package com.example.bitsbids3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;


@Entity   // helps map SQL table with this class
@Table(name = "test")
@Data
public class User {

    @Id   //this makes user_id the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // this generates a random value for user_id
    private long user_id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email")
    private String email;
    public User(){

    }

    public <E> User(String email, String s, ArrayList<E> es) {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(long user_id, String username, String password, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
    }


}

