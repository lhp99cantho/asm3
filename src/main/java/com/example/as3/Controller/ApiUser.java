package com.example.as3.Controller;

import com.example.as3.Entity.User;
import com.example.as3.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ApiUser {
    @Autowired
    protected UserService userService;

    //Get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getUser () {
        List<User> list = userService.getAllUsers();
        return list != null ? ResponseEntity.ok(list)
                : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Register user - C
    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody User user) {
        User rs = userService.createOrUpdateUser(user);
        return rs != null ? ResponseEntity.ok("Registered.")
                : ResponseEntity.ofNullable("Registering failed.");
    }

    //Get user - R
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user)
                : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Update user - U
    @PutMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User rs = userService.createOrUpdateUser(user);
        return user != null ? ResponseEntity.ok(user)
                : new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Delete user - D
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        boolean rs = userService.deleteUserById(id);
        return rs ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
