package com.example.demo.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Category is mandatory")
    private String category;

    @Column(name = "pet_type", nullable = false)
    private String petType;

    @Column(nullable = false)
    @NotBlank(message = "Frequency is mandatory")
    private String frequency;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "finish_date", nullable = false)
    private LocalDateTime finishDate;

    @Column(nullable = false)
    @NotBlank(message = "Zone is mandatory")
    private String zone;

    @Column(nullable = false)
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Duration is mandatory")
    private String duration;

    @Column(nullable = false)
    @NotNull(message = "Cost is mandatory")
    private BigDecimal cost;

    @Column
    private String score;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User provider;

    @OneToMany(mappedBy = "service")
//    @JsonManagedReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "service")
    private List<Booking> bookings;
}
