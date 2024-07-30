package com.Expenses_sharing_Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Expenses_sharing_Application.model.User;
import com.Expenses_sharing_Application.service.UserService;

@RestController
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping("/saveuser")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getalluser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
