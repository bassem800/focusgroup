package com.innovation.center.focusgroup.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Entity
public class Survey {
    @Id
    @GeneratedValue
    private int id;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnoreProperties({"surveys"})
    private Company company;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "survey")
    private List<Question> questions;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "survey")
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Answer> answers;

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    private String name;
    private boolean closed;
    private boolean published;

    public Survey() {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        closed = false;
        published = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean equals(Object o) {
        return ((Survey) o).id == this.id;
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

    public void setCompany(Company company) {
        this.company = company;
    }
}