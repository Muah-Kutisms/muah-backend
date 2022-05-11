package com.muah.muahbackend.domain.user.controller;

import com.muah.muahbackend.domain.user.dto.LoginDto;
import com.muah.muahbackend.domain.user.dto.TokenDto;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.infra.config.security.JwtFilter;
import com.muah.muahbackend.infra.util.TokenProvider;
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
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        System.out.println("##################check1#####################" + authentication );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt =  tokenProvider.createToken(authentication);
//        System.out.println("##################check2#####################" + jwt );
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer "+jwt);
//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
//    }

    @PostMapping("/authenticate2")
    public TokenDto authorize2(@Valid @RequestBody LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if (user.isPresent()) {
            return new TokenDto(tokenProvider.createToken(loginDto.getEmail()));
        } else {
            System.out.println("can't find");
        }
        return null;
    }
}
