package com.example.quiz_crud.controller;

import com.example.quiz_crud.model.AppUser;
import com.example.quiz_crud.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin // important for HTML → backend
public class AuthController {

    @Autowired
    private AppUserRepository repo;

    // ✅ USER REGISTER
    @PostMapping("/register")
    public String register(@RequestBody AppUser appUser) {

        // check duplicate username
        if (repo.findByUsername(appUser.getUsername()) != null) {
            return "Username already exists";
        }

        // 🔥 IMPORTANT FIX
        appUser.setRole("USER");   // auto assign role
        appUser.setEnabled(true);

        repo.save(appUser);
        return "User Registered Successfully";
    }

    // ✅ LOGIN (ADMIN & USER)
    @PostMapping("/login")
    public AppUser login(@RequestBody AppUser user) {

        AppUser dbUser = repo.findByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );

        return dbUser; // frontend checks null or role
    }

    // optional delete
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
        return "User deleted";
    }
}
