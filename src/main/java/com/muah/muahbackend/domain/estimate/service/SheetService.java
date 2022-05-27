package com.muah.muahbackend.domain.estimate.service;

import com.muah.muahbackend.domain.estimate.dto.*;
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
    public ArrayList<SheetNumberDto> getSheetList() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        Long id;
        ArrayList<SheetNumberDto> sheetList = new ArrayList<>();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        }else{
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());

        Collection<Pet> petData = petRepository.findAllByOwner(user);
        ArrayList<PetDto> pets = petData.stream().map(r -> new PetDto(r)).collect(toCollection(ArrayList::new));


        if (pets.size() != 0){
            for(PetDto pet : pets){
                id = pet.getId();
                List<Sheet> sheetsData = sheetRepository.findAllByPetIdArray(id);

                for (int i = 0 ; i < sheetsData.size() ; ++i){

                    int finalI = i + 1;
                    String petName = sheetsData.get(i).getPetName() +" " + finalI;
                    sheetList.add(new SheetNumberDto(sheetsData.get(i), petName));
                    System.out.println(sheetList); }
            }
        }else{throw new SheetNotFoundException();}

        return sheetList;
    }


    @Transactional(readOnly = true)
    public SheetDto getSheets(Long id) {
        Sheet sheetData = sheetRepository.findById(id).orElseThrow(() -> new SheetNotFoundException());
        SheetDto result = new SheetDto(sheetData);
        System.out.println(result);
        return result;
    }

    @Transactional(readOnly = true)
    public ArrayList<SheetDto> getPetsheet(Long id){
        Pet petData = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException());
        Long petId = petData.getId();

        Collection<Sheet> sheetsData = sheetRepository.findAllByPetId(petId);

        ArrayList<SheetDto> sheets = sheetsData.stream().map(s -> new SheetDto(s)).collect(toCollection(ArrayList::new));
        System.out.println(sheets);

        return sheets;
    }


    @Transactional
    public SheetUploadResponse uploadSheet(SheetUploadRequest request){
        Long id = request.getPetId();
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException());

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

    @Transactional
    public SheetDto updateSheet(Long id, SheetUpdateDto request){
        return sheetRepository.findById(id)
                .map(s -> {
                    s.setQuestion(request.getQuestion());
                    s.setWay(request.getWay());
                    s.setService(request.getService());
                    s.setLocation(request.getLocation());
                    s.setFuneralDate(request.getFuneralDate());

                    SheetDto sdto = new SheetDto(sheetRepository.save(s));
                    System.out.println(sdto);    //요것두 그러네요,,
                    return sdto;
                })
                .orElseThrow(() -> {
                    throw new SheetNotFoundException();
                });
    }


    @Transactional
    public boolean deleteSheet(Long id){
        Sheet sheet = sheetRepository.findById(id).orElseThrow(() -> new SheetNotFoundException());
        sheetRepository.delete(sheet);
        return true;
    }

}


