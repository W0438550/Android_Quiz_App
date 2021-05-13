package com.example.quizbuilder;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import java.io.*;
import java.util.*;

public class QuizGame extends AppCompatActivity {

    //Defining all global variables
    TextView userName, displayScore, displayQuestion, questionNum;
    Button answer1, answer2, answer3, answer4;
    int maxScore = 10;
    int currentScore = 0;
    int currentQuestion = 0;
    int maxQuestion = 10;
    String msg, TAG = "Error";
    String str = null;
    BufferedReader br = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Connect the screen with the specific variables
        displayScore = findViewById(R.id.displayScore);
        displayQuestion = findViewById(R.id.displayQuestion);
        questionNum = findViewById(R.id.questionNum);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        //Intent and Bundle for End Screen
        final Bundle bundle = new Bundle();
        final Intent finalQuizScore = new Intent(this, FinalQuizScore.class);

        //Get the Name the User entered from Main Screen
        Intent intent = getIntent();
        String Name = intent.getStringExtra("userName");
        userName = findViewById(R.id.userName);
        userName.setText(Name);

        //Array and Hash Map defined
        final ArrayList<String> questions = new ArrayList<>();
        final ArrayList<String> answers = new ArrayList<>();
        final ArrayList<String> choices = new ArrayList<>();
        final Map<String, String> relation = new HashMap<>();


        //At the start of the quiz screen populate the arrays and buttons
        try {
            //region populate arrays with values from file raw.questionList
            InputStream is = getResources().openRawResource(R.raw.questionlist);
            br = new BufferedReader(new InputStreamReader(is));
            System.out.println("File in RAW is open");

            while ((str = br.readLine()) != null) {
                String[] content = str.split(":");
                questions.add(content[0]);
                answers.add(content[1]);
                relation.put(content[0], content[1]);
            }

            //populate buttons with values
            //display score
            displayScore.setText(currentScore + "/" + maxScore);
            //display question number
            questionNum.setText(currentQuestion + "/" + maxQuestion);
            //shuffle questions and answers
            Collections.shuffle(answers);
            Collections.shuffle(questions);
            //add the answer that corresponds to question which is inside choices array
            choices.add(relation.get(questions.get(0)));
            //get 3 wrong values from the answers array and compare them to the relations array
            for (int i = 1; i < 4; i++) {
                if (!answers.get(i).equals(relation.get(questions.get(0))) && !choices.contains(answers.get(i))) {
                    choices.add(answers.get(i));
                } else {
                    choices.add(answers.get(i + 1));
                }
            }
            //set Text for view and buttons
            Collections.shuffle(choices);
            displayQuestion.setText(questions.get(0));
            answer1.setText(choices.get(0));
            answer2.setText(choices.get(1));
            answer3.setText(choices.get(2));
            answer4.setText(choices.get(3));

        } catch (IOException e) {
            msg = e.getLocalizedMessage();
            Log.e(TAG, msg != null ? msg : "File information could not be retrieved");
        } catch (Exception e) {
            msg = e.getLocalizedMessage();
            Log.e(TAG, msg != null ? msg : "General error when populating the array");
        }


        //The first button will trigger the events below
        answer1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    //go to finalQuizScore when question number reaches 10
                    currentQuestion++;
                    if (currentQuestion >= 10 && answer1.getText().equals(relation.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                    // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer and display the appropriate toast message
                    } else if (answer1.getText().equals(relation.get(questions.get(0)))) {
                        Toast.makeText(QuizGame.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                        currentScore++;
                        displayScore.setText(currentScore + "/" + maxScore);
                    //display toast message for wrong message
                    } else {
                        Toast.makeText(QuizGame.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
                    }

                    //display the current score
                    questionNum.setText(currentQuestion + "/" + maxQuestion);
                    //remove the question that was just displayed from the array list and clear the choices array list
                    questions.remove(0);
                    choices.clear();
                    //shuffle questions again to get a new question on position 0
                    Collections.shuffle(questions);
                    //add correct answer to choices array
                    choices.add(relation.get(questions.get(0)));
                    //add 3 wrong choices to the choices arraylist
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(relation.get(questions.get(0))) && !choices.contains(answers.get(i))) {
                            choices.add(answers.get(i));
                        } else {
                            choices.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(choices);
                    displayQuestion.setText(questions.get(0));
                    answer1.setText(choices.get(0));
                    answer2.setText(choices.get(1));
                    answer3.setText(choices.get(2));
                    answer4.setText(choices.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 1 error");
                }
            }
        });

        //The second button will trigger the events below
        answer2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    currentQuestion++;
                    if (currentQuestion >= 10 && answer2.getText().equals(relation.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer
                    } else if (answer2.getText().equals(relation.get(questions.get(0)))) {
                        Toast.makeText(QuizGame.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                        currentScore++;
                        displayScore.setText(currentScore + "/" + maxScore);
                    } else {
                        Toast.makeText(QuizGame.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
                    }

                    questionNum.setText(currentQuestion + "/" + maxQuestion);
                    questions.remove(0);
                    choices.clear();
                    Collections.shuffle(questions);
                    choices.add(relation.get(questions.get(0)));
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(relation.get(questions.get(0))) && !choices.contains(answers.get(i))) {
                            choices.add(answers.get(i));
                        } else {
                            choices.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(choices);
                    displayQuestion.setText(questions.get(0));
                    answer1.setText(choices.get(0));
                    answer2.setText(choices.get(1));
                    answer3.setText(choices.get(2));
                    answer4.setText(choices.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 2 error");
                }
            }
        });

        //The third button will trigger the events below
        answer3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    currentQuestion++;
                    if (currentQuestion >= 10 && answer3.getText().equals(relation.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer
                    } else if (answer3.getText().equals(relation.get(questions.get(0)))) {
                        Toast.makeText(QuizGame.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                        currentScore++;
                        displayScore.setText(currentScore + "/" + maxScore);
                    } else {
                        Toast.makeText(QuizGame.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
                    }

                    questionNum.setText(currentQuestion + "/" + maxQuestion);
                    questions.remove(0);
                    choices.clear();
                    Collections.shuffle(questions);
                    choices.add(relation.get(questions.get(0)));
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(relation.get(questions.get(0))) && !choices.contains(answers.get(i))) {
                            choices.add(answers.get(i));
                        } else {
                            choices.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(choices);
                    displayQuestion.setText(questions.get(0));
                    answer1.setText(choices.get(0));
                    answer2.setText(choices.get(1));
                    answer3.setText(choices.get(2));
                    answer4.setText(choices.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 3 error");
                }
            }
        });

        //The fourth button will trigger the events below
        answer4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    currentQuestion++;
                    if (currentQuestion >= 10 && answer4.getText().equals(relation.get(questions.get(0)))) {
                        currentScore++;
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer
                    }else if (currentQuestion >= 10) {
                        bundle.putString("userScore", String.valueOf(currentScore));
                        finalQuizScore.putExtras(bundle);
                        startActivity(finalQuizScore);
                        // check if user choose the correct answer
                    } else if (answer4.getText().equals(relation.get(questions.get(0)))) {
                        Toast.makeText(QuizGame.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                        currentScore++;
                        displayScore.setText(currentScore + "/" + maxScore);
                    } else {
                        Toast.makeText(QuizGame.this, "Incorrect Answer", Toast.LENGTH_SHORT).show();
                    }

                    questionNum.setText(currentQuestion + "/" + maxQuestion);
                    questions.remove(0);
                    choices.clear();
                    Collections.shuffle(questions);
                    choices.add(relation.get(questions.get(0)));
                    for (int i = 1; i < 4; i++) {
                        if (!answers.get(i).equals(relation.get(questions.get(0))) && !choices.contains(answers.get(i))) {
                            choices.add(answers.get(i));
                        } else {
                            choices.add(answers.get(i + 1));
                        }
                    }
                    //set Text for view and buttons
                    Collections.shuffle(choices);
                    displayQuestion.setText(questions.get(0));
                    answer1.setText(choices.get(0));
                    answer2.setText(choices.get(1));
                    answer3.setText(choices.get(2));
                    answer4.setText(choices.get(3));
                } catch (Exception e) {
                    msg = e.getLocalizedMessage();
                    Log.e(TAG, msg != null ? msg : "Button 4 error");
                }
            }
        });
    }
}