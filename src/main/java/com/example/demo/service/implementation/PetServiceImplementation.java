package com.example.demo.service.implementation;

import com.example.demo.persistence.model.Pet;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.PetRepository;
import com.example.demo.service.PetService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetServiceImplementation implements PetService {

    private final PetRepository petRepository;
    private final UserServiceImplementation userServiceImplementation;

    @Override
    public List<Pet> findByUser(String userEmail) {
        User user = this.userServiceImplementation.getUser(userEmail);
        return this.petRepository.findByUser(user);
    }

    public Pet addPet(String userEmail, Pet pet) {
        User user = this.userServiceImplementation.getUser(userEmail);
        pet.setUser(user);
        return this.petRepository.save(pet);
    }

    public void deletePet(String userEmail, Pet pet) {
        List<Pet> pets = this.findByUser(userEmail);

        for (Pet currentPet: pets) {
            if (currentPet.getName().equals(pet.getName())) {
                this.petRepository.delete(currentPet);
            }
        }
    }

    public void editPetObservations(String userEmail, Pet pet) {
        List<Pet> pets = this.findByUser(userEmail);

        for (Pet currentPet: pets) {
            if (currentPet.getName().equals(pet.getName())) {
                currentPet.setObservations(pet.getObservations());
                this.petRepository.save(currentPet);
            }
        }
    }

}
