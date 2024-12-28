package com.emard.quizzapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emard.quizzapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
    

}
