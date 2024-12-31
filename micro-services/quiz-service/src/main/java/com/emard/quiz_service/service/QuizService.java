package com.emard.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emard.quiz_service.dao.QuizDao;
import com.emard.quiz_service.feign.QuizInterface;
import com.emard.quiz_service.model.QuestionWrapper;
import com.emard.quiz_service.model.Quiz;
import com.emard.quiz_service.model.Response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizDao quizDao;
    private final QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title); 
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);  
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if(quiz.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromIds(quiz.get().getQuestionIds());
        return questions;
    }
    public ResponseEntity<String> calculateResult(Integer id, List<Response> responses) {
        int score = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>("Your score is: " + score + "/" + responses.size(), HttpStatus.OK);
    }

}
