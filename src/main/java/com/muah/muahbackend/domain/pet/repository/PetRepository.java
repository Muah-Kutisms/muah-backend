package com.muah.muahbackend.domain.pet.repository;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("from Pet p where p.owner = :owner")
    Collection<Pet> findAllByOwner(@Param("owner") User owner);
}