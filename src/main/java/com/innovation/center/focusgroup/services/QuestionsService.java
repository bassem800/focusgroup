package com.innovation.center.focusgroup.services;

import com.innovation.center.focusgroup.entities.*;
import com.innovation.center.focusgroup.repositories.QuestionsRepository;
import com.innovation.center.focusgroup.repositories.SurveysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final SurveysRepository surveysRepository;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository, SurveysRepository surveysRepository) {
        this.questionsRepository = questionsRepository;
        this.surveysRepository = surveysRepository;
    }

    public List<Question> getAllQuestions() {
        return questionsRepository.findAll();
    }

    public Question getQuestionById(int id) {
        return questionsRepository.findById(id).orElse(null);
    }

    public List<Question> getQuestionsBySurveyId(int surveyId) {
        return questionsRepository.findBySurveyId(surveyId);
    }

    public ResponseEntity<Question> createQuestion(Question question) {
        Survey survey = surveysRepository.findById(question.getSurvey().getId()).orElse(null);
        if (survey != null && !survey.isClosed() && !survey.isPublished()) {
            return new ResponseEntity<>(questionsRepository.save(question), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> updateQuestion(int surveyId, int questionId, Question updatedQuestion) {
        Survey survey = surveysRepository.findById(surveyId).orElse(null);
        Question existingQuestion = questionsRepository.findById(questionId).orElse(null);

        if (survey != null && existingQuestion != null && existingQuestion.getSurvey().getId() == surveyId) {
            // Check if the survey is open and not published
            if (!survey.isClosed() && !survey.isPublished()) {
                // Update the existing question properties
                existingQuestion.setQuestion(updatedQuestion.getQuestion());

                // If the question type is "RANGE," update min and max
                if (existingQuestion.getType() == QuestionType.RANGE && updatedQuestion.getType() == QuestionType.RANGE) {
                    if (existingQuestion instanceof RangeQuestion && updatedQuestion instanceof RangeQuestion) {
                        RangeQuestion existingRangeQuestion = (RangeQuestion) existingQuestion;
                        RangeQuestion updatedRangeQuestion = (RangeQuestion) updatedQuestion;

                        existingRangeQuestion.setMin(updatedRangeQuestion.getMin());
                        existingRangeQuestion.setMax(updatedRangeQuestion.getMax());
                    }
                }

                // If the question type is "MC," update choices
                if (existingQuestion.getType() == QuestionType.MC && updatedQuestion.getType() == QuestionType.MC) {
                    if (updatedQuestion instanceof MultipleChoiceQuestion) {
                        MultipleChoiceQuestion existingMCQuestion = (MultipleChoiceQuestion) existingQuestion;
                        MultipleChoiceQuestion updatedMCQuestion = (MultipleChoiceQuestion) updatedQuestion;

                        existingMCQuestion.setChoices(updatedMCQuestion.getChoices());
                    }
                }

                // Save the updated question
                return new ResponseEntity<>(questionsRepository.save(existingQuestion), HttpStatus.OK);
            } else {
                // Survey is closed or published, cannot update the question
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            // Survey or question not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    public void deleteQuestionById(int id) {
        questionsRepository.deleteById(id);
    }
}
