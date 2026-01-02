package com.example.quiz_crud.repository;

import com.example.quiz_crud.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    Score findByUserId(Long userId);

    List<Score> findAll();
}
