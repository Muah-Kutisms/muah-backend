package com.muah.muahbackend.domain.pet.service;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.dto.PetInfoUpdateDto;
import com.muah.muahbackend.domain.pet.dto.PetRegisterRequest;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.PetNotFoundException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import com.muah.muahbackend.global.vo.Image;
import com.muah.muahbackend.infra.aws.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toCollection;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    private final S3Uploader s3Uploader;

    @Transactional(readOnly = true)
    public List<PetDto> getPetList() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails)principal).getUsername();
        } else {
            email = principal.toString();
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        Collection<Pet> petData = petRepository.findAllByOwner(user);
        List<PetDto> pets = petData.stream().map(r -> new PetDto(r)).collect(toCollection(ArrayList::new));
        System.out.println(pets);
        return pets;

    }

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
    public Pet createPetInfo(PetRegisterRequest petInfo, MultipartFile imgFile){

        User user = userRepository.findById(petInfo.getUserId()).orElseThrow(() -> new UserNotFoundException());

        Pet pet = Pet.builder()
                .name(petInfo.getName())
                .owner(user)
               // .gender(petInfo.getGender())
                .weight(petInfo.getWeight())
                .birthdate(LocalDate.parse(petInfo.getBirthdate(), DateTimeFormatter.ISO_DATE))
                .kind(petInfo.getKind())
                .build();

        Image prevImage = pet.getImage();
        s3Uploader.deleteImage("pet", prevImage);

        Image image = s3Uploader.uploadImage(imgFile, "pet");
        pet.uploadImage(image);
        System.out.println(pet);
        return petRepository.save(pet);
    }

    @Transactional
    public PetDto updatePetInfo(PetInfoUpdateDto petInfo, Long id){
        return petRepository.findById(id)
                .map(pet -> {
                    pet.setName(petInfo.getName());
                 //   pet.setGender(petInfo.getGender());
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

    @Transactional
    public void uploadUserImage(MultipartFile uploadImage, Long id){
        Pet pet = petRepository.findById(id).orElseThrow(()->new PetNotFoundException());

        //기존 펫 사진 삭제
        Image prevImage = pet.getImage();
        s3Uploader.deleteImage("pet", prevImage);

        Image image = s3Uploader.uploadImage(uploadImage, "pet");
        pet.uploadImage(image);
        petRepository.save(pet);
    }

    @Transactional
    public void deleteUserImage(Long id){
        Pet pet = petRepository.findById(id).orElseThrow(()->new PetNotFoundException());

        //기존 펫 사진 삭제
        Image image = pet.getImage();
        s3Uploader.deleteImage("pet", image);

        petRepository.save(pet);
    }
}