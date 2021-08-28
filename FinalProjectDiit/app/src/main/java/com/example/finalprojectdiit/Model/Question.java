package com.example.finalprojectdiit.Model;

public class Question {
    public String text_question,option_1,option_2,option_3,option_4,img_question;

    public Question(String text_question, String option_1, String option_2, String option_3, String option_4, String img_question) {
        this.text_question = text_question;
        this.option_1 = option_1;
        this.option_2 = option_2;
        this.option_3 = option_3;
        this.option_4 = option_4;
        this.img_question = img_question;
    }

    public Question() {}

    public String getText_question() {
        return text_question;
    }

    public void setText_question(String text_question) {
        this.text_question = text_question;
    }

    public String getOption_1() {
        return option_1;
    }

    public void setOption_1(String option_1) {
        this.option_1 = option_1;
    }

    public String getOption_2() {
        return option_2;
    }

    public void setOption_2(String option_2) {
        this.option_2 = option_2;
    }

    public String getOption_3() {
        return option_3;
    }

    public void setOption_3(String option_3) {
        this.option_3 = option_3;
    }

    public String getOption_4() {
        return option_4;
    }

    public void setOption_4(String option_4) {
        this.option_4 = option_4;
    }

    public String getImg_question() {
        return img_question;
    }

    public void setImg_question(String img_question) {
        this.img_question = img_question;
    }
}
