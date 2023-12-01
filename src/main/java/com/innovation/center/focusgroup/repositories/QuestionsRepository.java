package com.innovation.center.focusgroup.repositories;

import com.innovation.center.focusgroup.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Integer> {

    List<Question> findBySurveyId(int surveyId);

    // You can add custom query methods if needed

}