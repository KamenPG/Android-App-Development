package com.myappcompany.kameng.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button go;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView result;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timer;
    Button playAgain;
    Button goButton;
    ConstraintLayout gameLayout;


    public void playAgain(View view){

        score = 0;
        numberOfQuestions = 0;
        newQuestion();
        timer.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000){


            @Override
            public void onTick(long l) {

                timer.setText(String.valueOf(l/1000 + "s"));

            }

            @Override
            public void onFinish() {

                timer.setText("0s");

                result.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public  void chooseAnswer(View view){


        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){

            result.setText("Correct! :)");
            score++;
        }

        else {

            result.setText("Wrong! :(");

        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();

    }



    public void start(View view) {

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));


    }

    public void newQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i=0; i<4; i++){

            if (i == locationOfCorrectAnswer){

                answers.add(a + b);
            }

            else {

                int wrongAnswer = rand.nextInt(41);

                while(wrongAnswer == a + b) {

                    wrongAnswer = rand.nextInt(41);

                }

                answers.add(wrongAnswer);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        result  = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        goButton = findViewById(R.id.goButton);
        timer = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);


        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
