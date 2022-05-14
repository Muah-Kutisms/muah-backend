package com.muah.muahbackend.domain.pet.repository;

import com.muah.muahbackend.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Transactional
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findById(Long id);

}
