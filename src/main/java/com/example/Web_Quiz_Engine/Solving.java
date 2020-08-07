package com.example.Web_Quiz_Engine;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotNull;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Solving {
    @JsonView

    private int[] answer;

    public Solving() {}

    public Solving(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        int[] x = null;
        if (Objects.equals(x, answer)){
            int[] ints = new int[0];
            return ints;
        }
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
