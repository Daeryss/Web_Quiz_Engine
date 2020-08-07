package com.example.Web_Quiz_Engine;


public class GetQuiz {
    private int id;
    private String title;
    private String text;
    private String[] options;


    public GetQuiz() {}

    public GetQuiz(int id, String title, String text, String[] options) {
        this.text = text;
        this.title = title;
        this.options = options;
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
}

