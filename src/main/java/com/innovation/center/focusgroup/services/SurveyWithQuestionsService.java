package com.innovation.center.focusgroup.services;

import com.innovation.center.focusgroup.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SurveyWithQuestionsService {
    @Autowired
    private SurveyService surveyService; // Assuming you have a SurveyService

    @Autowired
    private QuestionsService questionService; // Assuming you have a QuestionService

    public SurveyWithQuestionsDto getSurveyWithQuestions(int surveyId) {
        Survey survey = surveyService.getSurveyById(surveyId);
        if (survey != null) {
            List<QuestionDto> questionDtos = survey.getQuestions().stream()
                    .map(this::mapQuestionEntityToDto)
                    .collect(Collectors.toList());

            return new SurveyWithQuestionsDto(
                    survey.getId(),
                    survey.getName(),
                    survey.isClosed(),
                    survey.isPublished(),
                    questionDtos
            );
        }
        return null;
    }

    private QuestionDto mapQuestionEntityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setType(question.getType());

        // Assuming you have additional fields in QuestionDto based on the question type
        // Set those fields based on the question type
        if (question.getType() == QuestionType.MC) {
            // If it's a multiple-choice question, set choices
            MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
            questionDto.setChoices(mcQuestion.getChoices());
        } else if (question.getType() == QuestionType.RANGE) {
            // If it's a range question, set min and max
            RangeQuestion rangeQuestion = (RangeQuestion) question;
            questionDto.setMin(rangeQuestion.getMin());
            questionDto.setMax(rangeQuestion.getMax());
        }

        // You can continue this pattern for other question types

        return questionDto;
    }
}
