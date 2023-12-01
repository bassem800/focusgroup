package com.innovation.center.focusgroup.controller;

import com.innovation.center.focusgroup.entities.Answer;
import com.innovation.center.focusgroup.services.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswersController {

    private final AnswersService answersService;

    @Autowired
    public AnswersController(AnswersService answersService) {
        this.answersService = answersService;
    }

    @GetMapping("/api/survey/{id}/answers")
    public List<Answer> getAnswersBySurveyId(@PathVariable int id) {

        return answersService.getAnswersBySurveyId(id);
    }

    @PostMapping("/api/answers")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
        return answersService.createAnswer(answer);
    }

    @DeleteMapping("/api/answers/{id}")
    public ResponseEntity<Void> deleteAnswerById(@PathVariable int id) {
        answersService.deleteAnswerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Other methods...
}
