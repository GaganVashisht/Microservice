package com.project.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.user.service.entites.User;

public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);
    
}
