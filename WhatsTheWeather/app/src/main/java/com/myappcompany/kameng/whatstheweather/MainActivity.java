package com.myappcompany.kameng.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText cityTextView;
    TextView forecastType;
    TextView forecastTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = findViewById(R.id.cityTextView);
        forecastType = findViewById(R.id.result);
        forecastTemp = findViewById(R.id.tempResult);

    }

    public void search(View view){

        DownloadTask task = new DownloadTask();

        //Extracting from API

        task.execute("http://openweathermap.org/data/2.5/weather?q=" + cityTextView.getText().toString() + "&appid=b6907d289e10d714a6e88b30761fae22");

        //Hiding the keyboard

        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(cityTextView.getWindowToken(),0);


    }

    public class DownloadTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){

                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return  result;



            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(getApplicationContext(),"Invalid City", Toast.LENGTH_SHORT).show();

                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jsonObject = new JSONObject(result);

                //Selecting a specific key !!!

                JSONObject temp  = jsonObject.getJSONObject("main");

                    forecastTemp.setText(temp.getString("temp") + " °C");

            }
            catch (Exception e){

                e.printStackTrace();

                Toast.makeText(getApplicationContext(),"Invalid City", Toast.LENGTH_SHORT).show();

            }

            try {

                JSONObject jsonObject = new JSONObject(result);

                //Selecting a specific key !!!

                String weatherInfo = jsonObject.getString("weather");

                JSONArray arr = new JSONArray(weatherInfo);

                for (int i=0; i< arr.length(); i++){

                    JSONObject jsonPart = arr.getJSONObject(i);

                    //Selecting a specific value !!!

                    forecastType.setText(jsonPart.getString("main") + " | " + jsonPart.getString("description"));

                }
            }
            catch (Exception e){

                e.printStackTrace();

                Toast.makeText(getApplicationContext(),"Invalid City", Toast.LENGTH_SHORT).show();

            }


        }
    }


}
