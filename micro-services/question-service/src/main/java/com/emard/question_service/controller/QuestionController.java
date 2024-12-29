package com.emard.question_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emard.question_service.model.Question;
import com.emard.question_service.model.QuestionWrapper;
import com.emard.question_service.model.Response;
import com.emard.question_service.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate/{categoryName}/{numQuestions}")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
     @RequestParam int numQuestions) {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds) {
        return questionService.getQuestionsFromIds(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }

}