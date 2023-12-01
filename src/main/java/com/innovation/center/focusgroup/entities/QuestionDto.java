package com.innovation.center.focusgroup.entities;

import lombok.Getter;

import java.util.List;

@Getter
public class QuestionDto {
    // Getters and setters
    private int id;
    private String question;
    private QuestionType type;
    private List<String> choices;
    private Integer min;
    private Integer max;

    // Constructors, if needed

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

}
