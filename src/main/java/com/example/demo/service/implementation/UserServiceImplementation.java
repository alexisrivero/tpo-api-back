package com.example.demo.service.implementation;

import com.example.demo.exception.ResourceAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Override
    @Transactional
    public User addUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new ResourceAlreadyExistsException("Email already in use");
        }

        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User editUser(User updatedUser, String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setType(updatedUser.getType());

            return userRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    @Override
    public User getUser(String email) {
        User foundUser = this.foundUser(email);
        return foundUser;
    }

    private User foundUser (String email)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("We could not find a user with the given email");
        }
        return user.get();
    }

    public String resetPassword(User user) {
        user.setPassword("Jorgelin123");
        this.userRepository.save(user);

        return user.getPassword();
    }


    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

//    @Override
//    public String login(String email, String password) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//
//        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
//            return jwtUtil.generateToken(email);
//        } else {
//            throw new RuntimeException("Invalid email or password");
//        }
//    }

    @Override
    @Transactional
    public String login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
            } else if (optionalUser.get().getPassword().equals(password)){
            User user = optionalUser.get();
            System.out.println("-----------USER PASSWORD----------" + user.getPassword());
            System.out.println("-----------REQUEST PASSWORD--------" + password);
            System.out.println("TRUE O FALSE" + user.getPassword().equals(password));
            }

        return jwtUtil.generateToken(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserPrincipal(user);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }


}
