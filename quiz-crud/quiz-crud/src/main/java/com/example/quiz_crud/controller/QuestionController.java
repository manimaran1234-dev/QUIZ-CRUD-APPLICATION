package com.example.quiz_crud.controller;

import com.example.quiz_crud.model.Question;
import com.example.quiz_crud.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    // ADD QUESTION
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return service.addQuestion(question);
    }

    // GET ALL QUESTIONS
    @GetMapping
    public List<Question> getAllQuestions() {
        return service.getAllQuestions();
    }

    // UPDATE QUESTION
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id,
                                   @RequestBody Question question) {
        return service.updateQuestion(id, question);
    }

    // DELETE QUESTION
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        service.deleteQuestion(id);
        return "Question deleted successfully";
    }
}
