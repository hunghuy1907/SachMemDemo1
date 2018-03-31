package com.hungth.sachmemdemo.model;

/**
 * Created by ngoth on 3/31/2018.
 */

public class Question {
    private String chiaLop;
    private String type;
    private String value;
    private String result;

    public Question(String chiaLop, String type, String value, String result) {
        this.chiaLop = chiaLop;
        this.type = type;
        this.value = value;
        this.result = result;
    }

    public String getChiaLop() {
        return chiaLop;
    }

    public void setChiaLop(String chiaLop) {
        this.chiaLop = chiaLop;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
