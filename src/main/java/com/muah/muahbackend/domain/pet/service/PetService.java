package com.muah.muahbackend.domain.pet.service;

import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<PetDto> getPets() {
        Object principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        Collection<Pet> petData = petRepository.findAllByOwner(user);
        List<PetDto> pets = petData.stream().map(r -> new PetDto(r)).collect(toCollection(ArrayList::new));
        return pets;

    }


}