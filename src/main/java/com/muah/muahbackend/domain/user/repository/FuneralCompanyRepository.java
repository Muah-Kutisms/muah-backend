package com.muah.muahbackend.domain.user.repository;

import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.ProposalStatus;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface FuneralCompanyRepository extends JpaRepository<Sheet, Long> {

    @Query("from Proposal p where p.writer.id = :id and p.status = :status")
    List<Proposal> findAllByStatus(@Param("id") Long id, @Param("status") ProposalStatus status);

    @Query(
            "from Sheet s " +
            "join fetch Proposal p " +
            "on p.id = :id and p.sheet.id = s.id")
    List<Sheet> findByProposalId(@Param("id") Long id);




}
