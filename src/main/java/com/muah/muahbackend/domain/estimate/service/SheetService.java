package com.muah.muahbackend.domain.estimate.service;

import com.muah.muahbackend.domain.estimate.dto.SheetDto;
import com.muah.muahbackend.domain.estimate.dto.SheetUploadRequest;
import com.muah.muahbackend.domain.estimate.dto.SheetUploadResponse;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.repository.SheetRepository;
import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.PetNotFoundException;
import com.muah.muahbackend.global.error.exception.SheetNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toCollection;


@Slf4j
@Service
@RequiredArgsConstructor
public class SheetService {

    private final SheetRepository sheetRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public ArrayList<SheetDto> getSheetList() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        Long id;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

        Collection<Pet> petData = petRepository.findAllByOwner(user);
        ArrayList<PetDto> pets = petData.stream().map(r -> new PetDto(r)).collect(toCollection(ArrayList::new));
        System.out.println(pets);
        List<ArrayList> sheetsList = new ArrayList();

        if (pets.size() != 0){
            for (PetDto pet : pets) {
                id = pet.getId();
                Collection<Sheet> sheetsData = sheetRepository.findAllByPetId(id);
                ArrayList<SheetDto> sheets = sheetsData.stream().map(s -> new SheetDto(s)).collect(toCollection(ArrayList::new));
                if (sheets.size() != 0) sheetsList.add(sheets);

            }
        }else{throw new SheetNotFoundException();}

        ArrayList<SheetDto> mergedList = new ArrayList<>();
        for (ArrayList<SheetDto> sheet : sheetsList){
            mergedList.addAll(sheet);
        }
        System.out.println(mergedList);

        return mergedList;
    }


    @Transactional(readOnly = true)
    public List<SheetDto> getSheets(Long id) {


        Collection<Sheet> sheets = sheetRepository.findAllByPetId(id);
        ArrayList<SheetDto> sheetsList = sheets.stream().map(s-> new SheetDto(s)).collect(toCollection(ArrayList::new));
        return sheetsList;
    }


    @Transactional
    public SheetUploadResponse uploadSheet(SheetUploadRequest request){
        Pet pet = petRepository.findById(request.getPet().getId()).orElseThrow(() -> new PetNotFoundException());

        Sheet sheet = Sheet.builder()
                .pet(pet)
                .question(request.getQuestion())
                .funeralDate(request.getFuneralDate())
                .way(request.getWay())
                .service(request.getService())
                .location(request.getLocation())
                .build();

        return new SheetUploadResponse(sheetRepository.save(sheet).getId());


    }

}
