package com.example.Web_Quiz_Engine;

public class Answer {
    private boolean success;
    private String feedback;

    public Answer(){}

    public Answer(boolean success, String feedback){
        this.success = success;
        this.feedback = feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isSuccess() {
        return success;
    }
}