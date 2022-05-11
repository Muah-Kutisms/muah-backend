package com.muah.muahbackend.domain.user.controller;

import com.muah.muahbackend.domain.user.dto.LoginDto;
import com.muah.muahbackend.domain.user.dto.TokenDto;
import com.muah.muahbackend.domain.user.dto.TokenRequestDto;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.domain.user.service.UserAuthService;
import com.muah.muahbackend.infra.config.security.JwtFilter;
import com.muah.muahbackend.infra.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final UserAuthService userAuthService;


    @PostMapping("/authenticate")
    public TokenDto authorize(@Valid @RequestBody LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if (user.isPresent()) {
            return new TokenDto(tokenProvider.createToken(loginDto.getEmail()), tokenProvider.createRefreshToken() );
        } else {
            System.out.println("can't find");
        }
        return null;
    }

    @PostMapping("/reissue")
    public TokenDto reIssue(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenDto responseDto = userAuthService.reIssue(tokenRequestDto);
        return responseDto;
    }
}
