package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalQuizScore extends AppCompatActivity {

    TextView displayScore, message;
    Button buttonExit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        try {
            //Get the user's score from QuizGame Activity
            Intent intent = getIntent();
            String userScore = intent.getStringExtra("userScore");

            //Connect screen with variables
            displayScore = findViewById(R.id.displayScore);
            message = findViewById(R.id.message);
            buttonExit = findViewById(R.id.buttonExit);

            //display the score and the message
            displayScore.setText(userScore + "/10");
            message.setText("Thank you for playing!");

            //Exit button
            buttonExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //close application with exit button
                    finishAffinity();
                    System.exit(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}