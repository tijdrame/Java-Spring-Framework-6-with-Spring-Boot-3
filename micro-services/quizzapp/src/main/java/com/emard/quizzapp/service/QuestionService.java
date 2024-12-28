package com.emard.quizzapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emard.quizzapp.dao.QuestionDao;
import com.emard.quizzapp.model.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return ResponseEntity.ok(questionDao.findAll());
        } catch (Exception e) {
            return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return ResponseEntity.ok(questionDao.findByCategory(category));
        } catch (Exception e) {
            return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<String>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Failed to add question", HttpStatus.BAD_REQUEST);
        }
    }

}
