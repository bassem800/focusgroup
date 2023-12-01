package com.innovation.center.focusgroup.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import javax.persistence.*;


@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextQuestion.class, name = "TEXT"),
        @JsonSubTypes.Type(value = RangeQuestion.class, name = "RANGE"),
        @JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MC")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="question_type", discriminatorType = DiscriminatorType.STRING)
public class Question {
    private @Id @GeneratedValue int id;
    private String question;
    protected QuestionType type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
    @JsonIgnoreProperties({"name", "questions", "closed"})
    private Survey survey;

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean equals(Object o) {
        return ((Question) o).id == this.id;
    }



}