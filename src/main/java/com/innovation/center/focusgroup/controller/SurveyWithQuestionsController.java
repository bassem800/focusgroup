package com.innovation.center.focusgroup.controller;

import com.innovation.center.focusgroup.entities.SurveyWithQuestionsDto;
import com.innovation.center.focusgroup.services.SurveyWithQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/surveys-with-questions")
public class SurveyWithQuestionsController {
    @Autowired
    private SurveyWithQuestionsService surveyWithQuestionsService;

    @GetMapping("/{id}")
    public ResponseEntity<SurveyWithQuestionsDto> getSurveyWithQuestions(@PathVariable int id) {
        SurveyWithQuestionsDto surveyWithQuestionsDto = surveyWithQuestionsService.getSurveyWithQuestions(id);
        return surveyWithQuestionsDto != null
                ? new ResponseEntity<>(surveyWithQuestionsDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
