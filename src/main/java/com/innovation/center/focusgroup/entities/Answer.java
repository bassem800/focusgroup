package com.innovation.center.focusgroup.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@Entity
public class Answer {
    private @Id @GeneratedValue int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"name", "questions", "closed"})
    private Survey survey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @ElementCollection
    private List<String> answer;

    public Answer() {
        answer = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public boolean equals(Object o) {
        return ((Answer) o).id == this.id;
    }
}
