package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.widget.*;

// References: Lecture Videos on BrightSpace

public class MainActivity extends AppCompatActivity {

    //region Global variables
    EditText userName;
    Button buttonStart;
    String msg, TAG = "Error";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect screen with variables
        buttonStart = findViewById(R.id.buttonStart);
        userName = findViewById(R.id.userName);
        //endregion

        //Intent and Bundle for Question Screen
        final Bundle bundle = new Bundle();
        final Intent quizGame = new Intent(this, QuizGame.class);
        //endregion

        //Start button click listener
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //check if string is empty
                    if (userName.getText().toString().isEmpty()) {
                        //Display a Toast message that asks for user name Input
                        Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    } else {
                        //save Name entered to the newly created bundle
                        bundle.putString("userName", userName.getText().toString());
//                    quizActivity.putExtra("NameOfUser",userName.getText().toString());
                        quizGame.putExtras(bundle);
                        //switch to the Quiz Window after putting the name to the bundle
                        startActivity(quizGame);
                    }
                } catch (Exception e) {
                    //log any error
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Get Error Name");
                }
            }
        });
    }
}