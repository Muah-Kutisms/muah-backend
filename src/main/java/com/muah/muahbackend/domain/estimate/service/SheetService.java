package com.muah.muahbackend.domain.estimate.service;

import com.muah.muahbackend.domain.estimate.dto.SheetDto;
import com.muah.muahbackend.domain.estimate.dto.SheetUploadRequest;
import com.muah.muahbackend.domain.estimate.dto.SheetUploadResponse;
import com.muah.muahbackend.domain.estimate.entity.Sheet;
import com.muah.muahbackend.domain.estimate.repository.SheetRepository;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.store.dto.ReviewUploadRequest;
import com.muah.muahbackend.domain.store.dto.ReviewUploadResponse;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.store.entity.Review;
import com.muah.muahbackend.domain.user.dto.UserDto;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.PetNotFoundException;
import com.muah.muahbackend.global.error.exception.ProductNotFoundException;
import com.muah.muahbackend.global.error.exception.SheetNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;


@Slf4j
@Service
@RequiredArgsConstructor
public class SheetService {

    private final SheetRepository sheetRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public List<SheetDto> getSheetList(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;


        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        Collection<Sheet> sheetsData = sheetRepository.findAllByUser(user);
        List<SheetDto> sheetsList = sheetsData.stream().map(s-> new SheetDto(s)).collect(toCollection(ArrayList::new));
        return sheetsList;

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
