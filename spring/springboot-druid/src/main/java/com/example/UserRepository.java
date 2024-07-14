package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
