package com.muah.muahbackend.domain.pet.repository;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.Optional;


@Transactional
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findById(Long id);

    @Query("from Pet p where p.owner = :owner")
    Collection<Pet> findAllByOwner(@Param("owner") User owner);
}