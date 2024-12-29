package com.emard.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emard.question_service.dao.QuestionDao;
import com.emard.question_service.model.Question;
import com.emard.question_service.model.QuestionWrapper;
import com.emard.question_service.model.Response;

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
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions) {
        try {
            List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);
            
            return ResponseEntity.ok(questionIds);
        } catch (Exception e) {
            return new ResponseEntity<List<Integer>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = questionDao.findAllById(questionIds);
        for (Question question : questions) {
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(question.getId());
            qw.setOption1(question.getOption1());
            qw.setOption2(question.getOption2());
            qw.setOption3(question.getOption3());  
            qw.setOption4(question.getOption4());
            qw.setQuestionTitle(question.getQuestionTitle());
            wrappers.add(qw);
        }
        return ResponseEntity.ok(wrappers);
    }
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for (Response response : responses) {
            Question question = questionDao.findById(response.getId()).get();
            if (question.getRightAnswer().equals(response.getResponse())) {
                score++;
            }
        }
        return ResponseEntity.ok(score);
    }

}
