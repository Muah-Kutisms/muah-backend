package com.muah.muahbackend.domain.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    public void request() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "email");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        String redirectURL = GOOGLE_BASE_URL + "?" + parameterString;
        System.out.println(redirectURL);
        try{
            response.sendRedirect(redirectURL);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
