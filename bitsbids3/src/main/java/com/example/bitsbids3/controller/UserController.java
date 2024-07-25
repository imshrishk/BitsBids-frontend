package com.example.bitsbids3.controller;

import com.example.bitsbids3.entity.User;
import com.example.bitsbids3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public Object addUser(@RequestBody User user)
    { if (userService.isUsernameExists(user.getUsername())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");}
        else if (userService.isEmailExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");}
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password
    ){         try {
            boolean isValid = userService.isValidUser(username, password);

            if (isValid) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login");
        }
    }
    
/* say i have a ui, in there i send data. if in the ui code, i write that the data inputted
 should go to /register then that is the place where the value will go when we refer to postmapping*/
}
