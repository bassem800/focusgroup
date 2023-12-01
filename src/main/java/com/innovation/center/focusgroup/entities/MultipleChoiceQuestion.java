package com.innovation.center.focusgroup.entities;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("MC")
public class MultipleChoiceQuestion extends Question {

    @ElementCollection
    private List<String> choices;

    public MultipleChoiceQuestion(){
        this.type = QuestionType.MC;
        choices = new ArrayList<>();
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}