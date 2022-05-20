package com.muah.muahbackend.domain.estimate.service;


import com.muah.muahbackend.domain.estimate.dto.*;
import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.repository.ProposalRepository;
import com.muah.muahbackend.domain.estimate.repository.SheetRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.ProposalNotFoundException;
import com.muah.muahbackend.global.error.exception.SheetNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;
    private final SheetRepository sheetRepository;

    @Transactional(readOnly = true)
    public List<ProposalDto> getProposalList(Long id){
        Collection<Proposal> proposals = proposalRepository.findAllBySheetId(id);
        ArrayList<ProposalDto> proposalList = proposals.stream().map(p -> new ProposalDto(p)).collect(toCollection(ArrayList::new));
        return proposalList;
    }

    @Transactional(readOnly = true)
    public ProposalDto getProposalInfo(Long id){
        Proposal proposalData = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException());
        ProposalDto result = new ProposalDto(proposalData);
        return result;
    }

    @Transactional
    public ProposalUploadResponse uploadProposal(Long sheetId, ProposalUploadRequest request){
        User user = userRepository.findById(request.getWriterId()).orElseThrow(() -> new UserNotFoundException());
        Sheet sheet = sheetRepository.findById(sheetId).orElseThrow(() -> new SheetNotFoundException());
        Proposal proposal = Proposal.builder()
                .sheet(sheet)
                .writer(user)
                .price(request.getPrice())
                .content(request.getContent())
                .build();

        return new ProposalUploadResponse(proposalRepository.save(proposal).getId());
    }

    @Transactional
    public ProposalDto updateProposal(Long proposalId, ProposalUpdateDto proposalInfo){
        return proposalRepository.findById(proposalId)
                .map(proposal -> {
                    proposal.setPrice(proposalInfo.getPrice());
                    proposal.setContent(proposalInfo.getContent());
                    return new ProposalDto(proposalRepository.save(proposal));
                })
                .orElseThrow(() -> {
                   throw new ProposalNotFoundException();
                });

    }

    @Transactional
    public boolean deleteProposal(Long proposalId){
        Proposal proposal = proposalRepository.findById(proposalId).orElseThrow(() -> new ProposalNotFoundException());
        proposalRepository.delete(proposal);
        return true;
    }

}
