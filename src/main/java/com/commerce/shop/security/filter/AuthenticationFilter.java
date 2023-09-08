package com.commerce.shop.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.commerce.shop.model.dto.Account;
import com.commerce.shop.security.request.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

            try {
                Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
                Authentication authentication = new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword());
                System.out.println(account.getEmail());
                return  authenticationManager.authenticate(authentication);
            }catch (IOException io){
                throw new RuntimeException();
            }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
      String token = JWT.create()
              .withSubject(authResult.getName())
              .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConstants.TOKEN_EXPIRATION))
              .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

      response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER+token);
        response.getWriter().write(token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}
