package com.example.android.starwarsquizz;

import java.util.ArrayList;
import java.util.List;

class CheckBoxAnswersKeeper{
    private List<String> correctAnswers = new ArrayList<>();
    private List<String> userAnswers = new ArrayList<>();

    CheckBoxAnswersKeeper(List<String> correctAnswers, List<String> userAnswers){
        try{
            this.correctAnswers.addAll(correctAnswers);
            this.userAnswers.addAll(userAnswers);
        } catch(java.lang.IllegalArgumentException ex) {
            System.err.println("llegalArgumentException: Both arguments should be strings " + ex.getMessage());
        } catch (java.lang.NullPointerException ex){
            System.err.println("NullPointerException: One or both of the arguments are NULL " + ex.getMessage());
        }
    }

    List<String> GetCorrectAnswer(){
        return this.correctAnswers;
    }

    List<String> GetUserAnswer(){
        return this.userAnswers;
    }

}
