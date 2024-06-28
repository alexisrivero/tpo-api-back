package com.example.demo.service.implementation;

import com.example.demo.persistence.repository.BookingRepository;
import com.example.demo.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImplementation implements BookingService {

    private final BookingRepository bookingRepository;


}
