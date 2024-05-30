package com.example.demo.Auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    void doRgister(Company companyDetails){
        if(companyDetails.getEmail().isEmpty() || companyDetails.getPassword().isEmpty() || companyDetails.getName().isEmpty()){
            throw new IllegalStateException("Must provide all the fileds {name, email , password}");
        }
        Optional<Company> companyAlreadyExist = this.authRepository.findCompanyByEmail(companyDetails.getEmail());
        if(companyAlreadyExist.isPresent()){
            throw new IllegalStateException("Company with that email already exists");
        }
        companyDetails.setPassword(hashPassword(companyDetails.getPassword()));
        this.authRepository.save(companyDetails);
    }

    String hashPassword(String password){
        PasswordEncoder passEncoder = new BCryptPasswordEncoder();
        return passEncoder.encode(password);
    }

    Boolean comparePassword(String password, String hashedPass){
        return new BCryptPasswordEncoder().matches(password, hashedPass);
    }

    Boolean doLogin(Company companyDetails) {
        if(companyDetails.getEmail().isEmpty() || companyDetails.getPassword().isEmpty()){
            throw new IllegalStateException("Must provide all the fileds {email , password}");
        }
        Optional<Company> cmp = this.authRepository.findCompanyByEmail(companyDetails.getEmail());
        if(!cmp.isPresent()){
            return false;
        }
        return comparePassword(companyDetails.getPassword(), cmp.get().getPassword());
    }

    
}
