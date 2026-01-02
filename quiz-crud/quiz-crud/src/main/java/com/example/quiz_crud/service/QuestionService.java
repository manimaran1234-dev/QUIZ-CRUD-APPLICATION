package com.example.quiz_crud.service;

import com.example.quiz_crud.model.Question;
import com.example.quiz_crud.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question addQuestion(Question question) {
        return repository.save(question);
    }

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        existing.setQuestion(updatedQuestion.getQuestion());
        existing.setOption1(updatedQuestion.getOption1());
        existing.setOption2(updatedQuestion.getOption2());
        existing.setOption3(updatedQuestion.getOption3());
        existing.setOption4(updatedQuestion.getOption4());
        existing.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

        return repository.save(existing);
    }

    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }
}
