package com.innovation.center.focusgroup.services;

import com.innovation.center.focusgroup.entities.Company;
import com.innovation.center.focusgroup.entities.Question;
import com.innovation.center.focusgroup.entities.Survey;
import com.innovation.center.focusgroup.repositories.SurveyProjection;
import com.innovation.center.focusgroup.repositories.SurveysRepository;
import com.innovation.center.focusgroup.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SurveyService {

    private final SurveysRepository surveysRepository;

    private final CompanyRepository companyRepository;

    @Autowired
    public SurveyService(SurveysRepository surveysRepository, CompanyRepository companyRepository) {
        this.surveysRepository = surveysRepository;
        this.companyRepository = companyRepository;
    }

    public List<SurveyProjection> getAllSurveys() {
        return surveysRepository.getSurveyProjection();
    }

    public List<SurveyProjection> getSurveysByName(String name) {
        return surveysRepository.getSurveyProjectionByName(name);
    }

    public List<SurveyProjection> getSurveysByCompany(Long companyId) {
        return surveysRepository.getSurveyProjectionByCompanyId(companyId);
    }

    public Survey getSurveyById(int id) {
        return surveysRepository.findById(id).orElse(null);
    }

    public Survey createSurvey(String name, Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + companyId));

        Survey survey = new Survey();
        survey.setName(name);
        survey.setCompany(company);

        return surveysRepository.save(survey);
    }

    public ResponseEntity<Survey> updateSurvey(int id, Survey updatedSurvey) {
        Survey existingSurvey = surveysRepository.findById(id).orElse(null);

        if (existingSurvey != null) {
            // Update survey properties
            existingSurvey.setName(updatedSurvey.getName());
            // Add other survey properties that you want to update

            // Update questions (assuming Survey has a list of questions)
            List<Question> existingQuestions = existingSurvey.getQuestions();
            List<Question> updatedQuestions = updatedSurvey.getQuestions();

            // Update existing questions or add new questions
            for (Question updatedQuestion : updatedQuestions) {
                Question existingQuestion = findQuestionById(existingQuestions, updatedQuestion.getId());

                if (existingQuestion != null) {
                    // Update existing question properties
                    existingQuestion.setQuestion(updatedQuestion.getQuestion());
                    // Add other question properties that you want to update
                } else {
                    // Add new question to the survey
                    existingQuestions.add(updatedQuestion);
                }
            }

            // Remove questions that are not present in the updated survey
            existingQuestions.removeIf(question -> !updatedQuestions.contains(question));

            // Save the updated survey
            return new ResponseEntity<>(surveysRepository.save(existingSurvey), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Question findQuestionById(List<Question> questions, int questionId) {
        return questions.stream()
                .filter(question -> question.getId() == questionId)
                .findFirst()
                .orElse(null);
    }

    public void deleteSurveyById(int id) {
        surveysRepository.deleteById(id);
    }

    public ResponseEntity<Survey> closeSurvey(int id) {
        Survey existingSurvey = surveysRepository.findById(id).orElse(null);

        if (existingSurvey != null) {
            // Set closed to true and unpublished if the survey is currently published
            if (!existingSurvey.isClosed()) {
                existingSurvey.setClosed(true);
                if (existingSurvey.isPublished()) {
                    existingSurvey.setPublished(false);
                }
                return new ResponseEntity<>(surveysRepository.save(existingSurvey), HttpStatus.OK);
            } else {
                // The survey is already closed
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Survey> publishSurvey(int id) {
        Survey existingSurvey = surveysRepository.findById(id).orElse(null);

        if (existingSurvey != null) {
            if (!existingSurvey.isClosed()) {
                if (!existingSurvey.isPublished()) {
                    existingSurvey.setPublished(true);
                    return new ResponseEntity<>(surveysRepository.save(existingSurvey), HttpStatus.OK);
                } else {
                    // The survey is already published
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                // The survey is closed, cannot publish
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Survey> unpublishSurvey(int id) {
        Survey existingSurvey = surveysRepository.findById(id).orElse(null);

        if (existingSurvey != null) {
            // Unpublish the survey if it is published and not closed
            if (existingSurvey.isPublished() && !existingSurvey.isClosed()) {
                existingSurvey.setPublished(false);
                return new ResponseEntity<>(surveysRepository.save(existingSurvey), HttpStatus.OK);
            } else {
                // Cannot unpublish the survey
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Survey> openSurvey(int id) {
        Survey existingSurvey = surveysRepository.findById(id).orElse(null);

        if (existingSurvey != null) {
            // Open the survey and make it unpublished
            existingSurvey.setClosed(false);
            existingSurvey.setPublished(false);
            return new ResponseEntity<>(surveysRepository.save(existingSurvey), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
