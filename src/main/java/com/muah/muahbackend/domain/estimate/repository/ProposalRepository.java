package com.muah.muahbackend.domain.estimate.repository;

import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("from Proposal p where p.sheet.id = :id")
    Collection<Proposal> findAllBySheetId(@Param("id") Long id);

}
