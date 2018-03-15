package com.myappcompany.kameng.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double valueInLeva;
    double valueInEuro;

    public void ConvertCurrency(View view){


        EditText editText =(EditText) findViewById(R.id.editText);

        if (!editText.getText().toString().isEmpty()){

            valueInLeva = Double.parseDouble(editText.getText().toString());

            valueInEuro =  valueInLeva * 0.51;

            Log.i("Value" , String.valueOf(valueInEuro));

            Toast.makeText(this, "BGN " + valueInLeva + " is € " + valueInEuro, Toast.LENGTH_LONG).show();

        }

        else {

            Toast.makeText(this, "Please enter a value!" , Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
