package com.muah.muahbackend.domain.user.service;

import com.muah.muahbackend.domain.user.dto.RegisterRequest;
import com.muah.muahbackend.domain.user.dto.UserResponse;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserRepository userRepository;

    @Transactional
    public boolean register(RegisterRequest registerRequest) {
        User user = registerRequest.convert();
        userRepository.save(user);

        return true;
    }

}
