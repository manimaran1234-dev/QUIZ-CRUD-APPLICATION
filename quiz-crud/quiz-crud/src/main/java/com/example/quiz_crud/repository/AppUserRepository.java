package com.example.quiz_crud.repository;

import com.example.quiz_crud.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsernameAndPassword(String username, String password);

    AppUser findByUsername(String username);
}
