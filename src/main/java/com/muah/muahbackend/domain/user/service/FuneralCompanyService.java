package com.muah.muahbackend.domain.user.service;


import com.muah.muahbackend.domain.estimate.dto.SheetDto;
import com.muah.muahbackend.domain.estimate.dto.SheetNumberDto;
import com.muah.muahbackend.domain.estimate.entity.Proposal;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.repository.ProposalRepository;
import com.muah.muahbackend.domain.estimate.repository.SheetRepository;
import com.muah.muahbackend.domain.store.dto.ProductMenuDto;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.user.dto.FuneralUserDto;
import com.muah.muahbackend.domain.user.repository.FuneralCompanyRepository;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.muah.muahbackend.domain.estimate.entity.ProposalStatus.COMPLETE;
import static com.muah.muahbackend.domain.estimate.entity.ProposalStatus.RESERVED;
import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class FuneralCompanyService {

    private final FuneralCompanyRepository funeralCompanyRepository;
    private final UserRepository userRepository;
    private final ProposalRepository proposalRepository;
    private final SheetRepository sheetRepository;

    @Transactional(readOnly = true)
    public ArrayList getSheets(Long id){

        ArrayList response = new ArrayList<>();
        List<SheetDto> mergedComplete = new ArrayList<>();
        List<SheetDto> mergedReserved = new ArrayList<>();

        FuneralUserDto funeralCompany = new FuneralUserDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
        System.out.println(funeralCompany);

        // 소비자가 돈 지불해서 거래 완료된 sheet list
        List<Proposal> chargedProposal = funeralCompanyRepository.findAllByStatus(id, COMPLETE);
        ArrayList<Collection<SheetDto>> sheetCompleteList = new ArrayList<>();

        for (Proposal p : chargedProposal) {
            List<Sheet> sheets = funeralCompanyRepository.findByProposalId(p.getId());
            sheetCompleteList.add(sheets.stream().map(s -> new SheetDto(s)).collect(toCollection(ArrayList::new)));
        }
        System.out.println(sheetCompleteList);


        // 장례식장의 예약승인 기다리는 sheet list
        List<Proposal> waitingProposal = funeralCompanyRepository.findAllByStatus(id, RESERVED);
        ArrayList<Collection<SheetDto>> sheetReservedList = new ArrayList<>();

        for (Proposal p : waitingProposal) {
            List<Sheet> sheets = funeralCompanyRepository.findByProposalId(p.getId());
            sheetReservedList.add(sheets.stream().map(s -> new SheetDto(s)).collect(toCollection(ArrayList::new)));
        }
        System.out.println(sheetReservedList);


        for (Collection<SheetDto> s : sheetCompleteList) {
            mergedComplete.addAll(s);
        }

        for (Collection<SheetDto> s : sheetReservedList) {
            mergedReserved.addAll(s);
        }


        response.add(funeralCompany);
        response.add(mergedReserved);
        response.add(mergedComplete);

        return response;
    }

    @Transactional(readOnly = true)
    public List<SheetDto> getAllSheets(){

        List<Sheet> sheets= sheetRepository.findAll();
        ArrayList<SheetDto> sheetList = sheets.stream().map(s-> new SheetDto(s)).collect(toCollection(ArrayList::new));
        System.out.println(sheetList);
        return sheetList;
    }
}