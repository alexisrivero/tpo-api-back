package com.example.demo.web.controller;

import com.example.demo.persistence.model.Service;
import com.example.demo.service.ServiceService;
import com.example.demo.service.implementation.ServiceServiceImplementation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service")
public class ServiceController {

    private final ServiceServiceImplementation serviceServiceImplementation;

    @PostMapping("/add/{userEmail}")
    public ResponseEntity<Service> addService(@PathVariable String userEmail, @RequestBody @NotNull Service service) {
        Service createdService = serviceServiceImplementation.addService(userEmail, service);
        return ResponseEntity.ok(createdService);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<List<Service>> findByProviderEmail(@PathVariable String userEmail) {
        List<Service> services = this.serviceServiceImplementation.findByProvider(userEmail);
        return ResponseEntity.ok(services);
    }

    @DeleteMapping("/delete/{userEmail}")
    public ResponseEntity<Void> deleteService(@PathVariable String userEmail, @RequestBody @NotNull Service service) {
        this.serviceServiceImplementation.deleteService(userEmail, service);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit/{userEmail}")
    public ResponseEntity<Void> editService(@PathVariable String userEmail, @RequestBody @NotNull Service service) {
        this.serviceServiceImplementation.editService(userEmail, service);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Service>> findAllServices() {
        List<Service> services = this.serviceServiceImplementation.findAll();
        return ResponseEntity.ok(services);
    }
}
