package com.innovation.center.focusgroup.controller;

import com.innovation.center.focusgroup.entities.Question;
import com.innovation.center.focusgroup.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    @Autowired
    public QuestionsController(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionsService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        Question question = questionsService.getQuestionById(id);
        return question != null
                ? new ResponseEntity<>(question, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bySurvey/{surveyId}")
    public List<Question> getQuestionsBySurveyId(@PathVariable int surveyId) {
        return questionsService.getQuestionsBySurveyId(surveyId);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return questionsService.createQuestion(question);
    }

    @PutMapping("/bySurvey/{surveyId}/question/{questionId}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable int surveyId,
            @PathVariable int questionId,
            @RequestBody Question updatedQuestion) {
        return questionsService.updateQuestion(surveyId, questionId, updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable int id) {
        questionsService.deleteQuestionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
