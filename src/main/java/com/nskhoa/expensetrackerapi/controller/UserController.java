package com.nskhoa.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nskhoa.expensetrackerapi.entity.User;
import com.nskhoa.expensetrackerapi.entity.UserModel;
import com.nskhoa.expensetrackerapi.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(userService.readUser(id), HttpStatus.OK);
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        User updatedUser = userService.update(user, id);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> DeleteUserById(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
