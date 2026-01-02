package com.example.quiz_crud.controller;

import com.example.quiz_crud.dto.AnswerDTO;
import com.example.quiz_crud.model.Question;
import com.example.quiz_crud.model.Score;
import com.example.quiz_crud.repository.QuestionRepository;
import com.example.quiz_crud.repository.ScoreRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {

    private final QuestionRepository questionRepo;
    private final ScoreRepository scoreRepo;

    public QuizController(QuestionRepository questionRepo, ScoreRepository scoreRepo) {
        this.questionRepo = questionRepo;
        this.scoreRepo = scoreRepo;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    @PostMapping("/submit/{userId}")
    public String submitQuiz(
            @PathVariable Long userId,
            @RequestBody List<AnswerDTO> answers) {

        int score = 0;

        for (AnswerDTO answer : answers) {
            Question q = questionRepo.findById(answer.getQuestionId()).orElse(null);
            if (q != null && q.getCorrectAnswer().equals(answer.getSelectedOption())) {
                score++;
            }
        }

        Score s = new Score();
        s.setUserId(userId);
        s.setScore(score);

        scoreRepo.save(s);

        return "Quiz Submitted Successfully";
    }

    @GetMapping("/score/{userId}")
    public int getScore(@PathVariable Long userId) {
        Score s = scoreRepo.findByUserId(userId);
        return s != null ? s.getScore() : 0;
    }
}
