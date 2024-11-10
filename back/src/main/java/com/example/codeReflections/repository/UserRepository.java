package com.example.codeReflections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.codeReflections.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
