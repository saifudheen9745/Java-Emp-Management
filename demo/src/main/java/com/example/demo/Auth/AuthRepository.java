package com.example.demo.Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  AuthRepository extends JpaRepository<Company, Long>{
    Optional<Company> findCompanyByEmail(String email);
}
