package com.innovation.center.focusgroup.services;

import com.innovation.center.focusgroup.entities.Answer;
import com.innovation.center.focusgroup.entities.Survey;
import com.innovation.center.focusgroup.repositories.AnswersRepository;
import com.innovation.center.focusgroup.repositories.SurveysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersService {

    private final AnswersRepository answersRepository;
    private final SurveysRepository surveysRepository;

    @Autowired
    public AnswersService(AnswersRepository answersRepository, SurveysRepository surveysRepository) {
        this.answersRepository = answersRepository;
        this.surveysRepository = surveysRepository;
    }

    public List<Answer> getAnswersBySurveyId(int surveyId) {

        return answersRepository.findBySurveyId(surveyId);
    }

    public ResponseEntity<Answer> createAnswer(Answer answer) {
        Survey survey = surveysRepository.findById(answer.getSurvey().getId()).orElse(null);
        if (survey != null && survey.isPublished() && !survey.isClosed()) {
            return new ResponseEntity<>(answersRepository.save(answer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteAnswerById(int id) {
        answersRepository.deleteById(id);
    }

    // Other methods as needed
}
