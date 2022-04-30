package com.muah.muahbackend.domain.estimate.repository;

import com.muah.muahbackend.domain.estimate.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
}
