package com.muah.muahbackend.domain.estimate.repository;

import com.muah.muahbackend.domain.estimate.entity.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetRepository extends JpaRepository<Sheet, Long> {

}
