package com.example.demo.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "contact_phone_number", nullable = false)
    private String contactPhoneNumber;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Column(name = "schedule_reference", nullable = false)
    private String scheduleReference;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String state;

}
