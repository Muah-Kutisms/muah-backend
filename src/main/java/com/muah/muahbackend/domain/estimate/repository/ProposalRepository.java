package com.muah.muahbackend.domain.estimate.repository;

import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.ProposalStatus;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    @Query("from Proposal p where p.sheet.id = :id")
    Collection<Proposal> findAllBySheetId(@Param("id") Long id);

    @Query("from Proposal p where p.writer.id = :id and p.sheet.id = :sheetId")
    Proposal findByUserandSheetId(@Param("id") Long id, @Param("sheetId") Long sheetId);

    @Query("from Proposal p where p.writer = :writer")
    List<Proposal> findByUserId(@Param("writer") User writer);

    @Query("from Proposal p where p.sheet.pet.owner = :user and p.status= 'RESERVED'")
    Collection<Proposal> findReservedByPetOwner(@Param("user") User user);

    @Query("from Proposal p where p.status='RESERVED' and p.writer= :writer")
    Collection<Proposal> findReservedByWriter(@Param("writer") User writer);

    @Query("from Proposal p where p.status='COMPLETED' and p.writer= :writer")
    Collection<Proposal> findCompletedByWriter(@Param("writer") User writer);

}
