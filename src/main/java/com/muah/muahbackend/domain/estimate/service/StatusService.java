package com.muah.muahbackend.domain.estimate.service;


import com.muah.muahbackend.domain.estimate.dto.ProposalUpdateStatusDto;
import com.muah.muahbackend.domain.estimate.dto.SheetUpdaateStatusDto;
import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.ProposalStatus;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.entity.SheetStatus;
import com.muah.muahbackend.domain.estimate.repository.ProposalRepository;
import com.muah.muahbackend.domain.estimate.repository.SheetRepository;
import com.muah.muahbackend.global.error.exception.ProposalNotFoundException;
import com.muah.muahbackend.global.error.exception.SheetNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class StatusService {

    private final SheetRepository sheetRepository;
    private final ProposalRepository proposalRepository;

    @Transactional
    public SheetStatus updateSheetStatus(Long id, SheetUpdaateStatusDto status){

        Sheet sheet = sheetRepository.findById(id).orElseThrow(() -> new SheetNotFoundException());
        System.out.println(status);
        sheet.setStatus(status.getSheetStatus());
        sheetRepository.save(sheet);

        return sheet.getStatus();
    }

    @Transactional
    public ProposalStatus updateProposalStatus(Long id, ProposalUpdateStatusDto status){
        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException());

        proposal.setStatus(status.getStatus());
        proposalRepository.save(proposal);

        return proposal.getStatus();

    }


}
