package com.myappcompany.kameng.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void ConvertCurreny(View view){




        Log.i("Info" ,"Button Pressed");

        EditText editText =(EditText) findViewById(R.id.editText);


        double valueInLeva = Double.parseDouble(editText.getText().toString());

        double valueInEuro =  valueInLeva * 0.51;

        Log.i("Value" , String.valueOf(valueInEuro));

        Toast.makeText(this, "BGN " + valueInLeva + " is €" + valueInEuro, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
