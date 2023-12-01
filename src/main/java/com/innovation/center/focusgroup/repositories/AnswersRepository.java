package com.innovation.center.focusgroup.repositories;

import com.innovation.center.focusgroup.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answer, Integer> {
    public List<Answer> findBySurveyId(int id);
}