package com.example.demo.persistence.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "pet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Species is mandatory")
    private String species;

    @Column
    private String breed;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @NotBlank(message = "Zone is mandatory")
    private String zone;

    @Column
    private String observations;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;


}
