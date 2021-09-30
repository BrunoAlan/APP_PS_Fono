package com.example.myapplication.common.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class OptionAnswer implements Serializable {
    private String correctAnswer;
    private ArrayList<String> errorList;

    public OptionAnswer() {
        correctAnswer = "";
        errorList = new ArrayList<>();
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(ArrayList<String> errorList) {
        this.errorList = errorList;
    }

    public void addError(String error){
        errorList.add(error);
    }

    public int getErrorQuiantity(){
        return errorList.size();
    }
}
