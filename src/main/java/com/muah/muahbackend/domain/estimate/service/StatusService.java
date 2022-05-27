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
    public SheetStatus updateSheetStatus(Long id){

        Sheet sheet = sheetRepository.findById(id).orElseThrow(() -> new SheetNotFoundException());
        SheetStatus status = sheet.getStatus();

        if (status == SheetStatus.WAITING_RESERVATION) sheet.setStatus(SheetStatus.WAITING_APPROVAL);
        else if (status == SheetStatus.WAITING_APPROVAL) sheet.setStatus(SheetStatus.WAITING_PAYMENT);
        else if (status == SheetStatus.WAITING_PAYMENT) sheet.setStatus(SheetStatus.RESERVATION_CONFIRMED);

        return sheet.getStatus();
    }

    @Transactional
    public ProposalStatus updateProposalStatus(Long id){
        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException());
        ProposalStatus status = proposal.getStatus();

        if (status == ProposalStatus.PROPOSED) proposal.setStatus(ProposalStatus.RESERVED);
        else if (status == ProposalStatus.RESERVED) proposal.setStatus(ProposalStatus.APPROVED);
        else if (status == ProposalStatus.APPROVED) proposal.setStatus(ProposalStatus.COMPLETE);

        return proposal.getStatus();

    }


}
