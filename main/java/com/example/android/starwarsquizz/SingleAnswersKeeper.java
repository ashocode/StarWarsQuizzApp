package com.example.android.starwarsquizz;


 class SingleAnswersKeeper {
    private String correctAnswer = "";
    private String userAnswer = "";

     SingleAnswersKeeper(String correctAnswer, String userAnswer) {
        try {
            this.correctAnswer = correctAnswer;
            this.userAnswer = userAnswer;
        } catch (java.lang.IllegalArgumentException ex) {
            System.err.println("llegalArgumentException: Both arguments should be strings " + ex.getMessage());
        } catch (java.lang.NullPointerException ex) {
            System.err.println("NullPointerException: One or both of the arguments are NULL " + ex.getMessage());
        }
    }

     String GetCorrectAnswer() {
        return this.correctAnswer;
    }

     String GetUserAnswer() {
        return this.userAnswer;
    }
}
