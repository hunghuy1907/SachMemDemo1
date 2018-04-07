package com.hungth.sachmemdemo.model;

/**
 * Created by Admin on 4/4/2018.
 */

public class Data {
    private String questionTextAndAnswer;
    private String note;

    public Data(String questionTextAndAnswer, String note) {
        this.questionTextAndAnswer = questionTextAndAnswer;
        this.note = note;
    }

    public String getQuestionTextAndAnswer() {
        return questionTextAndAnswer;
    }

    public void setQuestionTextAndAnswer(String questionTextAndAnswer) {
        this.questionTextAndAnswer = questionTextAndAnswer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
