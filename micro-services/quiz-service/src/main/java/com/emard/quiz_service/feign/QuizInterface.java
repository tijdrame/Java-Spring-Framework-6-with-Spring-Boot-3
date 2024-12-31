package com.emard.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.emard.quiz_service.model.QuestionWrapper;
import com.emard.quiz_service.model.Response;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/question/generate/{categoryName}/{numQuestions}")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
     @RequestParam int numQuestions) ;
    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds) ;

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) ;
}
