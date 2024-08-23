package com.fin.oopsproject.Model;

import com.fin.oopsproject.Controllers.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository2 extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
} 
