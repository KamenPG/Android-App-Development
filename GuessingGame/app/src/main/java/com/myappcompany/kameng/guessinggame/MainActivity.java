package com.myappcompany.kameng.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNum;

    public void guessClick(View view) {


        EditText editText = (EditText) findViewById(R.id.inputNumber);


        if (!editText.getText().toString().isEmpty() && editText.getText().toString().matches("\\d+")){

            int theNumber = Integer.parseInt(editText.getText().toString());


            if (theNumber > randomNum) {

                Toast.makeText(this, "Lower!", Toast.LENGTH_SHORT).show();

            }

            else if (theNumber < randomNum) {

                Toast.makeText(this, "Higher!", Toast.LENGTH_SHORT).show();

            }

            else if (editText.getText().toString().length() == 0){

                Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show();

            }

            else {

                Toast.makeText(this, "You got it! Try again! :)", Toast.LENGTH_SHORT).show();

                Random rand = new Random();

                randomNum = rand.nextInt(20) + 1;

            }
        }
        else {

            Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show();

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();

        randomNum = rand.nextInt(20) + 1;
    }
}
