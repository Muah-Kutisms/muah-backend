package com.muah.muahbackend.domain.pet.service;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.dto.PetInfoUpdateDto;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.PetNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PetDto getPetInfo(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {//널값확인
            System.out.println("이상하게 웃는 최예림"+pet.get());
            return new PetDto(pet.get());
        }
        else{
            throw new PetNotFoundException();
        }
    }

    @Transactional
    public Pet createPetInfo(PetDto petInfo){
        System.out.printf("서비스 접근" + petInfo.getUser_id());
        Optional<User> user = userRepository.findById(petInfo.getUser_id());
        System.out.printf(String.valueOf(user.get()));
        Pet pet = Pet.builder()
            .name(petInfo.getName())
                .owner(user.get())
            .gender(petInfo.getGender())
            .weight(petInfo.getWeight())
            .birthdate(petInfo.getBirthdate())
                .build();
        System.out.printf(String.valueOf(user.get()));
        return petRepository.save(pet);
    }

    @Transactional
    public PetDto updatePetInfo(PetInfoUpdateDto petInfo, Long id){
        return petRepository.findById(id)
                .map(pet -> {
                    pet.setName(petInfo.getName());
                    pet.setGender(petInfo.getGender());
                    pet.setWeight(petInfo.getWeight());
                    pet.setBirthdate(petInfo.getBirthdate());
                    return new PetDto(petRepository.save(pet));
                })
                .orElseThrow(() -> {
                    throw new PetNotFoundException();
                });
    }

    public boolean deletePetInfo(Long id) throws IllegalAccessError{
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            petRepository.delete(pet.get());
            return true;
        }
        return false;
    }
}