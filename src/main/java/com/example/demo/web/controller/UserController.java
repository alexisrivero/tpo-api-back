package com.example.demo.web.controller;


import com.example.demo.persistence.model.User;
import com.example.demo.service.implementation.UserServiceImplementation;
import com.example.demo.web.DTO.LoginRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImplementation userServiceImplementation;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @NotNull User user) {
        User createdUser =  userServiceImplementation.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<User> editUser(@RequestBody @NotNull User user, @PathVariable String email) {
        User editedUser = userServiceImplementation.editUser(user, email);
        return ResponseEntity.ok(editedUser);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userServiceImplementation.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userServiceImplementation.getUser(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) {
        User user = userServiceImplementation.getUser(email);

        String newPassword = userServiceImplementation.resetPassword(user);

        return ResponseEntity.ok("Your new password is: " + newPassword);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = userServiceImplementation.login(loginRequest.getEmail(), loginRequest.getPassword());
        System.out.println("TOKEN " + token);
        return ResponseEntity.ok(token);
    }

}
