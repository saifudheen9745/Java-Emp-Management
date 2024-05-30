package com.example.demo.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public void register(@RequestBody Company companyDetails) {
        this.authService.doRgister(companyDetails);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Company companyDetails) {
        try {
            Boolean isValidUser = this.authService.doLogin(companyDetails);
            if(isValidUser){
                return new ResponseEntity<>(new AuthResponse("Login Successful", true, this.jwtService.createJwtToken(companyDetails.getName(), companyDetails.getEmail())), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new AuthResponse("Login Failed", false, ""), HttpStatus.NOT_FOUND);
            }
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(new AuthResponse(e.getMessage(), false, ""), HttpStatus.NOT_FOUND);
        }
    }
    
    
}
