package com.muah.muahbackend.domain.user.service;

import com.muah.muahbackend.domain.user.dto.RegisterRequest;
import com.muah.muahbackend.domain.user.dto.TokenDto;
import com.muah.muahbackend.domain.user.dto.TokenRequestDto;
import com.muah.muahbackend.domain.user.dto.UserResponse;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.error.exception.InvalidRefreshTokenException;
import com.muah.muahbackend.global.error.exception.UserNotFoundException;
import com.muah.muahbackend.infra.util.TokenProvider;
import com.nimbusds.oauth2.sdk.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public boolean register(RegisterRequest registerRequest) {
        User user = registerRequest.convert();
        userRepository.save(user);

        return true;
    }

    /**
    * 토큰 재발행
    * @param requestDto
    * */
    @Transactional
    public TokenDto reIssue(TokenRequestDto requestDto){
        if( !tokenProvider.validateToken(requestDto.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        User user = findUserByToken(requestDto);
        String accessToken = tokenProvider.createToken(user.getEmail());
        String refreshToken = tokenProvider.createRefreshToken();

        user.updateRefreshToken(refreshToken);
        return new TokenDto(accessToken, refreshToken);

    }

    public User findUserByToken(TokenRequestDto requestDto) {
        Authentication auth = tokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

}
