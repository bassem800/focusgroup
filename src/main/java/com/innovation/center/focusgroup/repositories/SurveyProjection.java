package com.innovation.center.focusgroup.repositories;

import java.util.List;

public interface SurveyProjection {
    int getId();
    String getName();
    boolean getClosed();
    boolean getPublished();
    List<QuestionProjection> getQuestions(); // Assuming you have a QuestionProjection interface

    interface QuestionProjection {
        int getId();
        String getQuestion();

    }
}
