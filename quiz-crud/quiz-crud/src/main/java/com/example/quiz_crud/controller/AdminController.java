package com.example.quiz_crud.controller;

import com.example.quiz_crud.model.AppUser;
import com.example.quiz_crud.model.Question;
import com.example.quiz_crud.model.Score;
import com.example.quiz_crud.repository.AppUserRepository;
import com.example.quiz_crud.repository.QuestionRepository;
import com.example.quiz_crud.repository.ScoreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:9090")
public class AdminController {

    private final QuestionRepository questionRepo;
    private final AppUserRepository userRepo;
    private final ScoreRepository scoreRepo;

    public AdminController(
            QuestionRepository questionRepo,
            AppUserRepository userRepo,
            ScoreRepository scoreRepo) {

        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.scoreRepo = scoreRepo;
    }

    // ✅ ADD QUESTION
    @PostMapping("/question")
    public Question addQuestion(@RequestBody Question q) {
        return questionRepo.save(q);
    }

    // ✅ VIEW ALL QUESTIONS (Manage Quiz)
    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    // ✅ VIEW ALL USERS
    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }

    // ✅ VIEW ALL SCORES
    @GetMapping("/scores")
    public List<Score> getScores() {
        return scoreRepo.findAll();
    }

    // ✅ DELETE QUESTION
    @DeleteMapping("/question/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionRepo.deleteById(id);
        return "Question Deleted Successfully";
    }
}
