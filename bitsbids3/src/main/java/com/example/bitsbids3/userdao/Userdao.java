package com.example.bitsbids3.userdao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bitsbids3.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userdao extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    User findByUsername(String username);
    boolean existsByEmail(String email);
}
