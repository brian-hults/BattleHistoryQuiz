package com.example.android.battlehistoryquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> correctAnswers = new ArrayList<>();
    ArrayList<Boolean> numCorrect = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adds the correct answer Strings to an ArrayList for later comparison
        correctAnswers.add("" + R.string.q1_answer);
        correctAnswers.add("" + R.string.q2_a2);
        correctAnswers.add("" + R.string.q3_a3);
        correctAnswers.add("" + R.string.q4_a2);
        correctAnswers.add("" + R.string.q5_answer);
        correctAnswers.add("" + R.string.q6_a1);
        correctAnswers.add("" + R.string.q7_a4);

        // Initializes the ArrayList of booleans to keep track of which questions have been correctly answered.
        for (int i = 0; i <= 7; i++) {
            numCorrect.add(false);
        }
    }

    // Method to check the answers of the EnterText questions
    public void enterText() {
        TextView q1_answer = (TextView) findViewById(R.id.q1_answer_text);
        TextView q5_answer = (TextView) findViewById(R.id.q5_answer_text);

        String compare_qa1 = q1_answer.getText().toString();
        String compare_qa5 = q5_answer.getText().toString();

        if (compare_qa1.equalsIgnoreCase(getString(R.string.q1_answer))) {
            numCorrect.set(0, true);
            Log.v("enterText", "Answer 1 was detected as correct");
        } else {
            numCorrect.set(0, false);
        }

        if (compare_qa5.equalsIgnoreCase(getString(R.string.q5_answer))) {
            numCorrect.set(4, true);
            Log.v("enterText", "Answer 5 was detected as correct");
        } else {
            numCorrect.set(4, false);
        }
    }

    // Method to check the answers of all of the radio button questions
    public void radioButtons() {
        RadioButton question2 = (RadioButton) findViewById(R.id.q2_a2);
        RadioButton question3 = (RadioButton) findViewById(R.id.q3_a3);
        RadioButton question6 = (RadioButton) findViewById(R.id.q6_a1);
        RadioButton question7 = (RadioButton) findViewById(R.id.q7_a4);

        // if statements to check the answer to each radio button question
        if (question2.isChecked()) {
            numCorrect.set(1, true);
            Log.v("radioButtons", "Answer 2 was detected as correct");
        } else {
            numCorrect.set(1, false);
        }

        if (question3.isChecked()) {
            numCorrect.set(2, true);
            Log.v("radioButtons", "Answer 3 was detected as correct");
        } else {
            numCorrect.set(2, false);
        }

        if (question6.isChecked()) {
            numCorrect.set(5, true);
            Log.v("radioButtons", "Answer 6 was detected as correct");
        } else {
            numCorrect.set(5, false);
        }

        if (question7.isChecked()) {
            numCorrect.set(6, true);
            Log.v("radioButtons", "Answer 7 was detected as correct");
        } else {
            numCorrect.set(6, false);
        }
    }

    // Method to check the answer of the multiple choice CheckBox question
    public void checkBoxes() {
        CheckBox q4_a1 = (CheckBox) findViewById(R.id.q4_a1);
        CheckBox q4_a2 = (CheckBox) findViewById(R.id.q4_a2);
        CheckBox q4_a3 = (CheckBox) findViewById(R.id.q4_a3);
        CheckBox q4_a4 = (CheckBox) findViewById(R.id.q4_a4);

        // Checks that the three correct answers are selected and that the incorrect
        // answer is not selected.
        if (q4_a1.isChecked() && !(q4_a2.isChecked()) && q4_a3.isChecked() && q4_a4.isChecked()) {
            numCorrect.set(3, true);
            Log.v("checkBoxes", "All of the correct answers for question 4 were selected");
        } else {
            numCorrect.set(3, false);
        }
    }

    public void onSubmit(View v) {
        // Checks the answers of the two Enter Text questions
        enterText();
        // Checks the answers of the CheckBox question
        checkBoxes();
        // Checks the answers of the Radio Button questions
        radioButtons();

        // Counter to keep track of how many correct answers there are
        int correctCounter = 0;
        // Initializes a String to display the score and list which
        // questions are incorrect.
        String wrongQuestions = "";

        // loops through the boolean ArrayList to check answer status
        for (int i = 0; i < (numCorrect.size()-1); i++) {
            if (numCorrect.get(i)) {
                correctCounter++;
            } else {
                wrongQuestions += (i + 1) + ", ";
            }
        }

        // Calculates and displays the score percentage value
        double score = 100 * (correctCounter / 7.0);
        DecimalFormat df = new DecimalFormat("#.##");
        score = Double.parseDouble(df.format(score));
        wrongQuestions = "Your score is: " + score + "%" + "\nThese questions were incorrect: " + wrongQuestions;
        wrongQuestions = wrongQuestions.substring(0, wrongQuestions.length()-2);

        // Creates Toast messages to be used for win and lose conditions
        Toast correct = Toast.makeText(getApplicationContext(),"Congratulations! You aced the quiz! \nYour score is: " + score + "%",Toast.LENGTH_LONG);
        Toast wrong = Toast.makeText(getApplicationContext(),wrongQuestions, Toast.LENGTH_LONG);

        // Displays the given Toast message depending if the score is 100% or lower.
        if (correctCounter == 7) {
            correct.show();
        } else {
            wrong.show();
        }
    }
}
