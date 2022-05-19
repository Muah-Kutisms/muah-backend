package com.muah.muahbackend.domain.instructor.repository;

import com.muah.muahbackend.domain.instructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query("from Sheet s where s.pet.id = :id")
    Collection<Instructor> findAllByPetId(@Param("id") Long id);


}
