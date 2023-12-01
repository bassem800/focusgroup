package com.innovation.center.focusgroup.repositories;

import com.innovation.center.focusgroup.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveysRepository extends JpaRepository<Survey, Integer> {
    @Query("SELECT s FROM #{#entityName} s")
    List<SurveyProjection> getSurveyProjection();

    @Query("SELECT s FROM Survey s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<SurveyProjection> getSurveyProjectionByName(@Param("name") String name);

    @Query("SELECT s FROM Survey s WHERE s.company.id = :companyId")
    List<SurveyProjection> getSurveyProjectionByCompanyId(@Param("companyId") Long companyId);

}