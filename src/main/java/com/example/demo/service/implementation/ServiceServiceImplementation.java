package com.example.demo.service.implementation;

import com.example.demo.persistence.model.Comment;
import com.example.demo.persistence.model.Pet;
import com.example.demo.persistence.model.Service;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.ServiceRepository;
import com.example.demo.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImplementation implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final UserServiceImplementation userServiceImplementation;

    public Service addService(String userEmail, Service service) {
        User user = this.userServiceImplementation.getUser(userEmail);
        service.setProvider(user);
        serviceRepository.save(service);

        return service;
    }

    @Override
    public List<Service> findByProvider(String userEmail) {
        User user = this.userServiceImplementation.getUser(userEmail);
        List<Service> services =  this.serviceRepository.findByProvider(user);

        List<Comment> filteredComments = new ArrayList<>();

        for (Service service: services) {
            List<Comment> comments = service.getComments();
            for (Comment comment: comments) {
                if (!comment.isBlocked()){
                    filteredComments.add(comment);
                }
            }

            service.setComments(filteredComments);
            service.setScore(this.calculateServiceScore(comments));
            this.serviceRepository.save(service);
        }
        return services;
    }

    @Override
    public void deleteService(String userEmail, Service service) {
        List<Service> services = this.findByProvider(userEmail);

        for (Service currentService: services) {
            if (currentService.getName().equals(service.getName())) {
                this.serviceRepository.delete(currentService);
            }
        }
    }

    @Override
    public void editService(String userEmail, Service service) {
        List<Service> services = this.findByProvider(userEmail);

        for (Service currentService: services) {
            if (currentService.getName().equals(service.getName())) {
                currentService.setCategory(service.getCategory());
                currentService.setPetType(service.getPetType());
                currentService.setFrequency(service.getFrequency());
                currentService.setStartDate(service.getStartDate());
                currentService.setFinishDate(service.getFinishDate());
                currentService.setZone(service.getZone());
                currentService.setDescription(service.getDescription());
                currentService.setDuration(service.getDuration());
                currentService.setCost(service.getCost());

                this.serviceRepository.save(currentService);
            }
        }
    }

    @Override
    public List<Service> findAll() {
        return this.serviceRepository.findAll(Sort.by(Sort.Direction.ASC, "score"));
    }

    public Optional<Service> findById(long id) {
        return this.serviceRepository.findById(id);
    }


    public String calculateServiceScore(List<Comment> comments) {
        int totalScore = 0;
        int dividedBy = 0; // counter
        for (Comment comment: comments) {
            totalScore += Integer.parseInt(comment.getScore());
            dividedBy++;
        }

        int finalScore = totalScore / dividedBy;

        return String.valueOf(finalScore);
    }

}
