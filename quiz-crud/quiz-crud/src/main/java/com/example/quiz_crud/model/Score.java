package com.example.quiz_crud.model;

import jakarta.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;   // MUST be Long

    private int score;

    // getters & setters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
