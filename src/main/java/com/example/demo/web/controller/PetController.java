package com.example.demo.web.controller;

import com.example.demo.persistence.model.Pet;
import com.example.demo.persistence.model.User;
import com.example.demo.service.implementation.PetServiceImplementation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetServiceImplementation petServiceImplementation;

    @GetMapping("/{userEmail}")
    public ResponseEntity<List<Pet>> findByUserEmail(@PathVariable String userEmail) {
        List<Pet> pets = this.petServiceImplementation.findByUser(userEmail);
        return ResponseEntity.ok(pets);
    }

    @PostMapping("/add/{userEmail}")
    public ResponseEntity<Pet> addPet(@PathVariable String userEmail, @RequestBody @NotNull Pet pet) {
        Pet createdPet = petServiceImplementation.addPet(userEmail, pet);
        return ResponseEntity.ok(createdPet);
    }

    @DeleteMapping("/delete/{userEmail}")
    public ResponseEntity<Void> deletePet(@PathVariable String userEmail, @RequestBody @NotNull Pet pet) {
        petServiceImplementation.deletePet(userEmail, pet);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit/{userEmail}")
    public ResponseEntity<Void> editPet(@PathVariable String userEmail, @RequestBody @NotNull Pet pet) {
        petServiceImplementation.editPetObservations(userEmail, pet);
        return ResponseEntity.noContent().build();
    }


}
