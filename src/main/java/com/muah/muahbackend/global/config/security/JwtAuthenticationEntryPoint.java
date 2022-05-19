package com.muah.muahbackend.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muah.muahbackend.global.error.ErrorCode;
import com.muah.muahbackend.global.error.ErrorResponse;
import com.muah.muahbackend.global.error.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authenticationException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
