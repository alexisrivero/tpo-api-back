package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Pet;
import com.example.demo.persistence.model.Service;
import com.example.demo.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByProvider(User user);
}
