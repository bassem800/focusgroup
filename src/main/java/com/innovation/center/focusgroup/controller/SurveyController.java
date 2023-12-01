package com.innovation.center.focusgroup.controller;

import com.innovation.center.focusgroup.entities.Survey;
import com.innovation.center.focusgroup.repositories.SurveyProjection;
import com.innovation.center.focusgroup.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "http://localhost:3000")
public class SurveyController {

    private final SurveyService surveyService;


    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public List<SurveyProjection> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable int id) {
        Survey survey = surveyService.getSurveyById(id);
        return survey != null
                ? new ResponseEntity<>(survey, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public List<SurveyProjection> getSurveysByName(@RequestParam String name) {
        return surveyService.getSurveysByName(name);
    }

    @GetMapping("/byCompany/{companyId}")
    public List<SurveyProjection> getSurveysByCompany(@PathVariable Long companyId) {
        return surveyService.getSurveysByCompany(companyId);
    }

    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        Long companyId = Long.parseLong(requestBody.get("companyId"));

        Survey survey = surveyService.createSurvey(name, companyId);
        return new ResponseEntity<>(survey, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable int id, @RequestBody Survey updatedSurvey) {
        return surveyService.updateSurvey(id, updatedSurvey);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveyById(@PathVariable int id) {
        surveyService.deleteSurveyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Survey> closeSurvey(@PathVariable int id) {
        return surveyService.closeSurvey(id);
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<Survey> publishSurvey(@PathVariable int id) {
        return surveyService.publishSurvey(id);
    }

    @PostMapping("/{id}/unpublish")
    public ResponseEntity<Survey> unpublishSurvey(@PathVariable int id) {
        return surveyService.unpublishSurvey(id);
    }

    @PostMapping("/{id}/open")
    public ResponseEntity<Survey> openSurvey(@PathVariable int id) {
        return surveyService.openSurvey(id);
    }
}
