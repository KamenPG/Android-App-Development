package com.myappcompany.kameng.currencyconverter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    TextView inEuro;
    double euro;
    EditText bgn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inEuro = findViewById(R.id.resultTextView);
        bgn = findViewById(R.id.editText);
    }

    public void ConvertCurrency(View view){

        DownloadTask task = new DownloadTask();

        //Extracting from API

        task.execute("http://data.fixer.io/api/latest?access_key=0d379f4e4f7c8916c29c440185165206");

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

                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jsonObject = new JSONObject(result);

                //Selecting a specific key !!!

                JSONObject ratesInfo = jsonObject.getJSONObject("rates");

                Double bgnRate = ratesInfo.getDouble("BGN");

                double bgnValue = Double.parseDouble(bgn.getText().toString());

                euro = bgnValue / bgnRate;

                inEuro.setText(String.format("€%.2f", euro));

//                String.format("%.2f", euro)





            }
            catch (Exception e){

                e.printStackTrace();
            }

//            try {
//
//                JSONObject jsonObject = new JSONObject(result);
//
//                //Selecting a specific key !!!
//
//                JSONObject value  = jsonObject.getJSONObject("rates");
//
//                inEuro.setText(value.getString("BGN"));
//
//
//            }
//            catch (Exception e){
//
//                e.printStackTrace();
//            }


        }
    }

}
