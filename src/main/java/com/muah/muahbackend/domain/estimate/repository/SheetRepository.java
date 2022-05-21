package com.muah.muahbackend.domain.estimate.repository;

import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional(readOnly = true)
public interface SheetRepository extends JpaRepository<Sheet, Long> {

    @Query("from Sheet s where s.pet.id = :id")
    Collection<Sheet> findAllByPetId(@Param("id") Long id);

    @Query("from Sheet s where s.pet.id = :id")
    List<Sheet> findAllByPetIdArray(@Param("id") Long id);

    @Query("from Sheet s where s.pet.owner = :user")
    Collection<Sheet> findAllByUser(@Param("user") User user);
}
