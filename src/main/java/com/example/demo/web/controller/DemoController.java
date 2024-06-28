package com.example.demo.web.controller;

import com.example.demo.persistence.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.implementation.UserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final UserServiceImplementation userServiceImplementation;

    @GetMapping("/demo")
    private ResponseEntity<String> demo() {
        return ResponseEntity.ok("HOLAAA");
    }

}
