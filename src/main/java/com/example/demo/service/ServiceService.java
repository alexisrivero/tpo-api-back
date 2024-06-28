package com.example.demo.service;

import com.example.demo.persistence.model.Pet;
import com.example.demo.persistence.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    Service addService(String userEmail, Service service);

    List<Service> findByProvider(String userEmail);

    void deleteService(String userEmail, Service service);

    void editService(String userEmail, Service service);

    Optional<Service> findById(long id);

    List<Service> findAll();
}
