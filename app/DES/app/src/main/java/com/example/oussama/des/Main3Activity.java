package com.example.oussama.des;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Main3Activity extends AppCompatActivity {
    String json_string,login_mat,login_pass,ver;
    Intent test;
    String json_url;
    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        mProgress.setVisibility(View.VISIBLE);


        test=getIntent();

        if(test.hasExtra("matier")){

            ver = getIntent().getExtras().getString("matier");
            login_mat = getIntent().getExtras().getString("mat");
            login_pass = getIntent().getExtras().getString("pass");

        }else if(test.hasExtra("recours")){

            ver = getIntent().getExtras().getString("recours");
            login_mat = getIntent().getExtras().getString("mat");
            login_pass = getIntent().getExtras().getString("pass");

        }else if(test.hasExtra("group")){

            ver = getIntent().getExtras().getString("group");
            login_mat = getIntent().getExtras().getString("mat");
            login_pass = getIntent().getExtras().getString("pass");

        }else if(test.hasExtra("cours")){

            ver = getIntent().getExtras().getString("cours");
            login_mat = getIntent().getExtras().getString("mat");
            login_pass = getIntent().getExtras().getString("pass");

        }



        new BackgroungTask().execute();





    }



    class BackgroungTask extends AsyncTask<Void,Void,String>

    {

        String JSON_STRING;
        @Override
        protected void onPreExecute() {
            if(ver.equals("recours") || ver.equals("matier")){
            json_url = "http://10.0.3.2/get_matier.php";
            }else if (ver.equals("group")){
                json_url = "http://10.0.3.2/get_group.php";
            }else if (ver.equals("cours")){
                json_url = "http://10.0.3.2/get_emp.php";
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_mat","UTF-8")+"="+URLEncoder.encode(login_mat,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();




                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!= null){

                    stringBuilder.append(JSON_STRING+"\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            json_string = result;


            parseJSON ();

        }
    }



    public void parseJSON (){

        if(json_string==null)
        {
            Toast.makeText(getApplicationContext(),"Vous voulez Reconnecté",Toast.LENGTH_LONG).show();



        }
        else
        {
            if (ver.equals("matier")){
                Intent intent = new Intent(this,DisplayMat.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("mat",login_mat);
                intent.putExtra("pass",login_pass);
                startActivity(intent);
            }else if(ver.equals("recours")){
                Intent intent = new Intent(this,Recours.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("login_mat",login_mat);
                startActivity(intent);

            }else if (ver.equals("group")){
                Intent intent = new Intent(this,group.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("login_mat",login_mat);
                startActivity(intent);
            }else if (ver.equals("cours")){
                Intent intent = new Intent(this,Planning.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("mat",login_mat);
                intent.putExtra("pass",login_pass);
                startActivity(intent);
            }
            finish();
        }
    }




}

