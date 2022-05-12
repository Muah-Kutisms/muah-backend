package com.muah.muahbackend.domain.user.service;

import com.muah.muahbackend.domain.pet.entity.Pet;
import com.muah.muahbackend.domain.user.dto.UserDto;
import com.muah.muahbackend.domain.user.dto.UserInfoUpdateDto;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
                    return new UserDto(userRepository.save(user), null);
                })
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });
    }

    @Transactional(readOnly = true)
    public UserDto getUserInfo(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            System.out.println((user.get().getPets()));
            return new UserDto(user.get(), user.get().getPets());
        }
        else{
            throw new UserNotFoundException();
        }
    }

}
