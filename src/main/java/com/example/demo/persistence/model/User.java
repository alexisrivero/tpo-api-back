package com.example.demo.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Email is mandatory")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank(message = "Address is mandatory")
    private String address;

    @Column
    private String type;

    @OneToMany(mappedBy = "user")
    private List<Pet> pets;

    @OneToMany(mappedBy = "provider")
    private List<Service> services;

    @OneToMany(mappedBy = "user")
//    @JsonManagedReference
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}
