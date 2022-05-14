package com.muah.muahbackend.domain.user.service;

import com.muah.muahbackend.domain.pet.dto.PetDto;
import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.pet.repository.PetRepository;
import com.muah.muahbackend.domain.user.dto.UserDto;
import com.muah.muahbackend.domain.user.dto.UserInfoUpdateDto;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.entity.UserRole;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Transactional
    public UserDto updateUserInfo(UserInfoUpdateDto updateInfo, Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateInfo.getName());
                    user.setPhone(updateInfo.getPhone());
                    user.setAddress(updateInfo.getAddress().convert());
                    user.setRole(updateInfo.getRole());
                    user.setIsNew(updateInfo.getIsNew());
                    user.setIsApproved(updateInfo.getIsApproved());
                    return new UserDto(userRepository.save(user));
                })
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfo(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {

            UserDto response = new UserDto(user.get());
            setPets(user.get(),response);

            return response;
        }
        else{
            throw new UserNotFoundException();
        }
    }

    private void setPets(User owner, UserDto userDto) {
        Collection<Pet> petsData = petRepository.findAllByOwner(owner);
        ArrayList<PetDto> pets = petsData.stream().map(p-> new PetDto(p)).collect(toCollection(ArrayList::new));
        System.out.println(pets);

        userDto.setPets(pets);
    }

}
