package com.muah.muahbackend.domain.instructor.repository;

import com.muah.muahbackend.domain.instructor.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
