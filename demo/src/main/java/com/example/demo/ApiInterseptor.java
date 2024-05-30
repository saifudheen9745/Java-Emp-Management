package com.example.demo;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.Auth.AuthRepository;
import com.example.demo.Auth.Company;
import com.example.demo.Auth.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiInterseptor implements HandlerInterceptor{

    private final JwtService jwtService;
    private final AuthRepository authRepository;
    
    public ApiInterseptor(AuthRepository authRepository, JwtService jwtService){
        this.authRepository = authRepository;
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token = authHeader.substring(7, authHeader.length());
            Jws<Claims> data = this.jwtService.decodeJwtToken(token);
            String email = data.getBody().get("email").toString();
            Optional<Company> cmp = this.authRepository.findCompanyByEmail(email);
            if(cmp.isPresent()){
                return true;
            }
        }
        return false;
    }
}
