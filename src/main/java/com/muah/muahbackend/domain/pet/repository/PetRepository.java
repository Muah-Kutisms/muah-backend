package com.muah.muahbackend.domain.pet.repository;

import com.muah.muahbackend.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
