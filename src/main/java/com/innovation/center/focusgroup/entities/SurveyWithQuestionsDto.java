package com.innovation.center.focusgroup.entities;

import lombok.Getter;

import java.util.List;

@Getter
public class SurveyWithQuestionsDto {
    // Getters
    private int id;
    private String name;
    private boolean closed;
    private boolean published;
    private List<QuestionDto> questions;

    public SurveyWithQuestionsDto(int id, String name, boolean closed, boolean published, List<QuestionDto> questions) {
        this.id = id;
        this.name = name;
        this.closed = closed;
        this.published = published;
        this.questions = questions;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
