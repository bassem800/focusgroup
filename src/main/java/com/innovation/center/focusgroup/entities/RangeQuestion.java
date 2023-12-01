package com.innovation.center.focusgroup.entities;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("RANGE")
public class RangeQuestion extends Question {
    private int min;
    private int max;

    public RangeQuestion() {
        this.type = QuestionType.RANGE;
    }

    public void setMin(int m) {
        min = m;
    }

    public void setMax(int m) {
        max = m;
    }

}