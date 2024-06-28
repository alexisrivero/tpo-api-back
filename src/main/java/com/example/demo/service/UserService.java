package com.example.demo.service;

import com.example.demo.persistence.model.User;
import jakarta.transaction.Transactional;

public interface UserService {
    User getUser(String email);

    User addUser(User user);

    User editUser(User updatedUser, String email);

    void deleteUser(String email);
    String login(String email, String password);

    User getUserById(long id);

}
