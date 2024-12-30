package com.emard.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emard.quiz_service.dao.QuizDao;
import com.emard.quiz_service.model.QuestionWrapper;
import com.emard.quiz_service.model.Quiz;
import com.emard.quiz_service.model.Response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizDao quizDao;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = null;
        /*List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title); 
        quiz.setQuestions(questions);
        quizDao.save(quiz);*/
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);  
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if(quiz.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        List<QuestionWrapper> questions = new ArrayList<>();
        /*for(Question q: quiz.get().getQuestions()) {
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(q.getId());
            qw.setQuestionTitle(q.getQuestionTitle());
            qw.setOption1(q.getOption1());
            qw.setOption2(q.getOption2());
            qw.setOption3(q.getOption3());
            qw.setOption4(q.getOption4());
            questions.add(qw);
        }*/
        return ResponseEntity.ok(questions);
    }
    public ResponseEntity<String> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        /*List<Question> questions = quiz.getQuestions();
        int score = 0;
        int i = 0;
        for(Response response: responses) {
            if(questions.get(i).getRightAnswer().equals(response.getResponse())) {
                score++;
            }
            i++;
        }*/
        return new ResponseEntity<>("Your score is: " + 0 + "/" + 0, HttpStatus.OK);
    }

}
