package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
