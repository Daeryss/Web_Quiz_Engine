package com.example.Web_Quiz_Engine;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Quiz {
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    @Size(min = 2)
    private String[] options;
    private int[] answer;


    public Quiz() {}

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.text = text;
        this.title = title;
        this.options = options;
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

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
