package com.example.android.starwarsquizz;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//Class is used to store the Activity data such as answered questions, current progress and etc.
//There is a getter and setter for each field except the constants, which have only getter.
public class ActivityDataKeeper implements DataKeeper {

    private static final String COLOR_ENABLED = "#FFEA00";
    private static final String COLOR_DISABLED = "#9E9E9E";
    private static final int NUMBER_OF_QUESTIONS = 5;
    private static final int MAX_SCORE = 10;
    private static final int POINTS_FOR_SINGLE_ANSWER = 2;
    private static final int POINTS_FOR_MULTI_ANSWER = 1;
    private String userAnswerToQuestionOne = "";
    private List<String> userAnswersToQuestionTwo = new ArrayList<>();
    private String userAnswerToQuestionThree = "";
    private String userAnswerToQuestionFour = "";
    private String userAnswerToQuestionFive = "";
    private int questionOneMarked = 0;
    private int questionTwoMarked = 0;
    private int questionThreeMarked = 0;
    private int questionFourMarked = 0;
    private int questionFiveMarked = 0;
    private boolean checkboxOne=false;
    private boolean checkboxTwo=false;
    private boolean checkboxThree=false;
    private boolean checkboxFour=false;
    private CheckBox questionTwoAnswerOne;
    private CheckBox questionTwoAnswerTwo;
    private CheckBox questionTwoAnswerThree;
    private CheckBox questionTwoAnswerFour;
    private RadioGroup radioGroupOne;
    private EditText questionFour;
    private RadioGroup radioGroupThree;
    private RadioGroup radioGroupFive;
    private Button finish;
    private TextView progress;
    private String intNullEx = " Expected int, but received NULL!";
    private String booleanNullEx = " Expected boolean, but received NULL!";
    private String stringNullEx = " Expected String, but received NULL!";
    private String viewNullEx = " Expected View, but received NULL!";


    public void SetQuestionOneMarked(int value){
        try{
            this.questionOneMarked = value;
        } catch (NullPointerException e){
            System.out.println("questionOneMarked" + intNullEx + e.getMessage());
        }
    }

    public void SetQuestionTwoMarked(int value){
        try{
            this.questionTwoMarked = value;
        } catch (NullPointerException e){
            System.out.println("questionTwoMarked" + intNullEx + e.getMessage());
        }
    }

    public void SetQuestionThreeMarked(int value){
        try{
            this.questionThreeMarked = value;
        } catch (NullPointerException e){
            System.out.println("questionThreeMarked" + intNullEx + e.getMessage());
        }
    }

    public void SetQuestionFourMarked(int value){
        try{
            this.questionFourMarked = value;
        } catch (NullPointerException e){
            System.out.println("questionFourMarked" + intNullEx + e.getMessage());
        }
    }

    public void SetQuestionFiveMarked(int value){
        try{
            this.questionFiveMarked = value;
        } catch (NullPointerException e){
            System.out.println("questionFiveMarked" + intNullEx + e.getMessage());
        }
    }

    public void SetCheckedBoxMarkedOne(Boolean value){
        try{
            this.checkboxOne = value;
        } catch (NullPointerException e){
            System.out.println("checkboxOne" + booleanNullEx + e.getMessage());
        }
    }

    public void SetCheckedBoxMarkedTwo(Boolean value){
        try{
            this.checkboxTwo = value;
        } catch (NullPointerException e){
            System.out.println("checkboxTwo" + booleanNullEx + e.getMessage());
        }
    }

    public void SetCheckedBoxMarkedThree(Boolean value){
        try{
            this.checkboxThree = value;
        } catch (NullPointerException e){
            System.out.println("checkboxThree" + booleanNullEx + e.getMessage());
        }
    }

    public void SetCheckedBoxMarkedFour(Boolean value){
        try{
            this.checkboxFour = value;
        } catch (NullPointerException e){
            System.out.println("checkboxFour" + booleanNullEx + e.getMessage());
        }
    }

    public void ResetUserAnswersToQuestionTwo(){
        this.userAnswersToQuestionTwo.clear();
    }

    public void SetUserAnswerToQuestionOne(String value){
        try{
            this.userAnswerToQuestionOne = value;
        } catch (NullPointerException e){
            System.out.println("userAnswerToQuestionOne" + stringNullEx + e.getMessage());
        }
    }

    public void SetUserAnswerToQuestionThree(String value){
        try{
            this.userAnswerToQuestionThree = value;
        } catch (NullPointerException e){
            System.out.println("userAnswerToQuestionThree" + stringNullEx + e.getMessage());
        }
    }

    public void SetUserAnswerToQuestionFour(String value){
        try{
            this.userAnswerToQuestionFour = value;
        } catch (NullPointerException e){
            System.out.println("userAnswerToQuestionFour" + stringNullEx + e.getMessage());
        }
    }

    public void SetUserAnswerToQuestionFive(String value){
        try{
            this.userAnswerToQuestionFive = value;
        } catch (NullPointerException e){
            System.out.println("userAnswerToQuestionFive" + stringNullEx + e.getMessage());
        }
    }

    public void SetFinishView(View v){
        try{
            this.finish = (Button) v;
        }catch(NullPointerException e){
            System.out.println( "finish" + viewNullEx + e.getMessage());
        }

    }

    public void SetProgressView(View v){
        try{
            this.progress = (TextView) v;
        }catch(NullPointerException e){
            System.out.println( "progress" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionOne(View v){
        try{
            this.radioGroupOne = (RadioGroup)v;
        }catch(NullPointerException e){
            System.out.println( "radioGroupOne" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionTwoAnswerOne(View v){
        try{
            this.questionTwoAnswerOne = (CheckBox)v;
        }catch(NullPointerException e){
            System.out.println( "questionTwoAnswerOne" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionTwoAnswerTwo(View v){
        try{
            this.questionTwoAnswerTwo = (CheckBox)v;
        }catch(NullPointerException e){
            System.out.println( "questionTwoAnswerTwo" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionTwoAnswerThree(View v){
        try{
            this.questionTwoAnswerThree = (CheckBox)v;
        }catch(NullPointerException e){
            System.out.println( "questionTwoAnswerThree" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionTwoAnswerFour(View v){
        try{
            this.questionTwoAnswerFour = (CheckBox)v;
        }catch(NullPointerException e){
            System.out.println( "questionTwoAnswerFour" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionThree(View v){
        try{
            this.radioGroupThree = (RadioGroup)v;
        }catch(NullPointerException e){
            System.out.println( "radioGroupThree" + viewNullEx + e.getMessage());
        }

    }

    public void SetViewQuestionFour(View v){
        try{
            this.questionFour = (EditText)v;
        }catch(NullPointerException e){
            System.out.println( "questionFour" + viewNullEx + e.getMessage());
        }
    }

    public void SetViewQuestionFive(View v){
        try{
            this.radioGroupFive = (RadioGroup)v;
        }catch(NullPointerException e){
            System.out.println( "radioGroupFive" + viewNullEx + e.getMessage());
        }
    }

    public int GetMaxScore(){
        return MAX_SCORE;
    }

    public int GetPointsForSingleAnswerQuestions(){
        return POINTS_FOR_SINGLE_ANSWER;
    }

    public int GetPointsForMultiAnswerQuestions(){
        return POINTS_FOR_MULTI_ANSWER;
    }

    public int GetQuestionOneMarked() {
        return this.questionOneMarked;
    }

    public int GetQuestionTwoMarked() {
        return this.questionTwoMarked;
    }

    public int GetQuestionThreeMarked() {
        return this.questionThreeMarked;
    }

    public int GetQuestionFourMarked() {
        return this.questionFourMarked;
    }

    public int GetQuestionFiveMarked() {
        return this.questionFiveMarked;
    }

    public boolean GetCheckedBoxOneMarked() {
        return this.checkboxOne;
    }

    public boolean GetCheckedBoxTwoMarked() {
        return this.checkboxTwo;
    }

    public boolean GetCheckedBoxThreeMarked() {
        return this.checkboxThree;
    }

    public boolean GetCheckedBoxFourMarked() {
        return this.checkboxFour;
    }

    public String GetUserAnswerToQuestionOne() {
        return this.userAnswerToQuestionOne;
    }

    public List<String> GetUserAnswersToQuestionTwo(){
        return this.userAnswersToQuestionTwo;
    }

    public String GetUserAnswerToQuestionThree() {
        return this.userAnswerToQuestionThree;
    }

    public String GetUserAnswerToQuestionFour() {
        return this.userAnswerToQuestionFour;
    }

    public String GetUserAnswerToQuestionFive() {
        return this.userAnswerToQuestionFive;
    }

    public String GetColorEnabled(){
        return COLOR_ENABLED;
    }

    public String GetColorDisabled(){
        return COLOR_DISABLED;
    }

    public int GetNumberOfQuestions(){
        return NUMBER_OF_QUESTIONS;
    }

    public View GetFinishView(){
        return this.finish;
    }

    public View GetProgressView(){
        return this.progress;
    }

    public View GetViewQuestionTwoAnswerOne(){
        return this.questionTwoAnswerOne;
    }

    public View GetViewQuestionTwoAnswerTwo(){
        return this.questionTwoAnswerTwo;
    }

    public View GetViewQuestionTwoAnswerThree(){
        return this.questionTwoAnswerThree;
    }

    public View GetViewQuestionTwoAnswerFour(){
        return this.questionTwoAnswerFour;
    }

    public View GetViewQuestionOne(){
        return this.radioGroupOne;
    }

    public View GetViewQuestionThree(){
        return this.radioGroupThree;
    }

    public View GetViewQuestionFour(){
        return this.questionFour;
    }

    public View GetViewQuestionFive(){
        return this.radioGroupFive;
    }
}
