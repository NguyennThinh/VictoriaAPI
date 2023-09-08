package com.commerce.shop.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.commerce.shop.exception.BadCredentialsException;
import com.commerce.shop.exception.ConflictException;
import com.commerce.shop.exception.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandelFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }
        catch (JWTVerificationException jwt){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("JWT NOT VALID");
            response.getWriter().flush();
        } catch (NotFoundException e){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(e.getMessage());
                response.getWriter().flush();
        }catch (BadCredentialsException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
        }catch (ConflictException e){
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().write(e.getMessage());
            response.getWriter().flush();
        }
    }
}
