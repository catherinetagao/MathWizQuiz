package com.catherineorbe.mathlearningapp;

public class QuestionsItem {
    String questions, answer1, answer2, correct;

    public QuestionsItem(String questions, String answer1, String answer2, String correct) {
        this.questions = questions;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.correct = correct;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
