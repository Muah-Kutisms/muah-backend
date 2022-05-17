package com.muah.muahbackend.domain.pet.service;

import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.store.dto.ProductDto;
import com.muah.muahbackend.domain.store.entity.Product;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.error.exception.PetNotFoundException;
import com.muah.muahbackend.global.error.exception.ProductNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toCollection;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    public List<PetDto> getPetList() {
        Object principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        Collection<Pet> petData = petRepository.findAllByOwner(user);

        if (petData.isEmpty()){
            throw new PetNotFoundException();
        }

        List<PetDto> pets = petData.stream().map(r -> new PetDto(r)).collect(toCollection(ArrayList::new));
        return pets;

    }

    @Transactional(readOnly = true)
    public PetDto getPet(@PathVariable Long id){
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException());
        PetDto result = new PetDto(pet);
        return result;
    }



}