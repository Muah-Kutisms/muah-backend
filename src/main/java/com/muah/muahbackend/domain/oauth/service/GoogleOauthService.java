package com.muah.muahbackend.domain.oauth.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.muah.muahbackend.domain.oauth.dto.GoogleLoginDto;
import com.muah.muahbackend.domain.oauth.dto.GoogleLoginResponse;
import com.muah.muahbackend.domain.oauth.dto.UserLoginResponse;
import com.muah.muahbackend.domain.user.entity.User;
import com.muah.muahbackend.domain.user.repository.UserRepository;
import com.muah.muahbackend.global.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoogleOauthService {
    private final HttpServletResponse response;
    @Value("${google.url}")
    private String GOOGLE_BASE_URL;
    @Value("${google.client.id}")
    private String GOOGLE_CLIENT_ID;
    @Value("${google.callback.url}")
    private String GOOGLE_CALLBACK_URL;
    @Value("${google.client.secret}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${google.token.url}")
    private String GOOGLE_TOKEN_BASE_URL;
    @Value("${google.auth.url}")
    private String GOOGLE_AUTH_URL;

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public void request() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "email%20profile%20openid");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("redirect_uri", "http://localhost:8080/auth/google/callback");


        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));
        String redirectURL = GOOGLE_BASE_URL + "?" + parameterString;
        try{
            response.sendRedirect(redirectURL);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserLoginResponse userLogin(String code) {
        String email = getUserEmail(code);

        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()){
            User user = findUser.get();
            user.updateRefreshToken(tokenProvider.createRefreshToken());
            return new UserLoginResponse(user.getId(), user.getIsNew(), tokenProvider.createToken(user.getEmail()), user.getRefreshToken());
        }
        else {
            User newUser = saveUser(email);
            newUser.updateRefreshToken(tokenProvider.createRefreshToken());
            return new UserLoginResponse(newUser.getId(), newUser.getIsNew(), tokenProvider.createToken(newUser.getEmail()), newUser.getRefreshToken());
        }
    }

    private User saveUser(String email){
        User user = User.builder()
                .email(email)
                .password("1234")
                .build();
        return userRepository.save(user);
    }

    public String getUserEmail(String code) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("client_secret", GOOGLE_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_CALLBACK_URL);
        params.put("grant_type", "authorization_code");
        try{
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(GOOGLE_TOKEN_BASE_URL, params, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            GoogleLoginResponse googleLoginResponse = objectMapper.readValue(responseEntity.getBody(), new TypeReference<GoogleLoginResponse>() {});
            //
            String jwtToken = googleLoginResponse.getIdToken();

            String requestUrl = UriComponentsBuilder.fromHttpUrl(GOOGLE_AUTH_URL+"/tokeninfo").queryParam("id_token",jwtToken).toUriString();
            String resultJson = restTemplate.getForObject(requestUrl, String.class);

            if (resultJson != null) {
                GoogleLoginDto userinfoDto = objectMapper.readValue(resultJson, new TypeReference<GoogleLoginDto>() {});

                String email = userinfoDto.getEmail();

                return email;
            }
            else {
                throw new Exception("Google OAuth failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
