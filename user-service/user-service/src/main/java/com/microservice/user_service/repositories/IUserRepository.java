package com.microservice.user_service.repositories;

import com.microservice.user_service.entities.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {

    public Optional<User> findByUserEmail(String userEmail);
}
