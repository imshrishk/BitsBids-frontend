package com.fin.oopsproject.Controllers;

import com.fin.oopsproject.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public Iterable<UserModel> hello() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{userId}")
    public UserModel getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }


    @PostMapping(path = "/check")
    public Boolean checkUser(@RequestParam Long userId) {
        UserModel user = userService.getUserById(userId);
        return user != null;
    }

    @PostMapping(path = "/setHostel")
    public UserModel setHostel(@RequestParam Long userId, @RequestParam String hostel) {
        return userService.setHostel(userId, hostel);
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
    @PostMapping(path = "/setCampusId")
    public UserModel setCampusId(@RequestParam Long userId, @RequestParam String campusId) {
        return userService.setCampusId(userId, campusId);
    }

    @PostMapping(path = "/setPhone")
    public UserModel setPhone(@RequestParam Long userId, @RequestParam String phone) {
        return userService.setPhone(userId, phone);
    }
}
