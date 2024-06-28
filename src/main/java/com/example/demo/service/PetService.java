package com.example.demo.service;

import com.example.demo.persistence.model.Pet;
import com.example.demo.persistence.model.User;

import java.util.List;

public interface PetService {

    List<Pet> findByUser(String userEmail);

    Pet addPet(String userEmail, Pet pet);

    void deletePet(String userEmail, Pet pet);

    void editPetObservations(String userEmail, Pet pet);
}
