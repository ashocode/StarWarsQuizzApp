package com.example.android.starwarsquizz;

import java.util.List;


class ActivityScoreTracker implements ScoreTracker{

    private int singleAnswerPoints;
    private int multiAnswerPoints;
    private int currentScore = 0;

    ActivityScoreTracker(int pointsForSingleAnswer, int pointsForMultiAnswer){

        this.singleAnswerPoints = pointsForSingleAnswer;
        this.multiAnswerPoints = pointsForMultiAnswer;
    }

    //Goes trough the list of single-answer questions and compares correct answer with user answer and increments the points accordingly
    public void checkSingleAnswers(List<SingleAnswersKeeper> listOfAnswers){
        for (SingleAnswersKeeper answer:
                listOfAnswers) {
            if (answer.GetCorrectAnswer().equals(answer.GetUserAnswer())){
                currentScore +=singleAnswerPoints;
            }
        }
    }

    //Goes through the  selected checkboxes and compares tem with the correct answers.
    //If incorrect is marked the score is reduced to calculate correctly the score
    public void checkMultiAnswers(CheckBoxAnswersKeeper listOfAnswers){
        List<String> correctAnswers = listOfAnswers.GetCorrectAnswer();
        List<String> userAnswers = listOfAnswers.GetUserAnswer();

        for (String answer:
                userAnswers) {

            if (correctAnswers.contains(answer)){
                currentScore += multiAnswerPoints;
            } else {
                currentScore -= multiAnswerPoints;
            }
        }
    }

    //Returns the score
    public int calculateScore(){
        return currentScore;
    }
}
