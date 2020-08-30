package com.example.oussama.des;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Recours extends AppCompatActivity {

    EditText text_recours;
    String recours,login_mat,json_string,matier,note,res;
    Button valider;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView tv;
    Intent i1;

    Spinner spinner,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recours);
        text_recours = (EditText) findViewById(R.id.text_recours);
        valider = (Button) findViewById(R.id.valider);
        tv = (TextView) findViewById(R.id.textView);
        spinner =(Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner)  findViewById(R.id.spinner2);
        i1 = getIntent();
        if(i1.hasExtra("json_data")) {
            json_string = i1.getExtras().getString("json_data");
            add();
        }
        addnote();
        
    }


    public void valider(View view){
        note = spinner2.getSelectedItem().toString();
        matier = spinner.getSelectedItem().toString();
        login_mat = getIntent().getExtras().getString("login_mat");
        recours = text_recours.getText().toString();
        new BackgroundTask().execute();

    }

    public void annuler(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Annuler?");
        alertDialogBuilder.setMessage("Souhaitez-vous vraiment Vous Annuler l'opération?");
        alertDialogBuilder.setNegativeButton("NON",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alertDialogBuilder.setPositiveButton("OUI",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        finish();

                    }
                });
        alertDialogBuilder.show();
    }

    public void add() {
        List<String> list = new ArrayList<String>();




        try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                String matier1;
                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    matier1 = JO.getString("nom_matier");
                    list.add(matier1);
                    count++;
                }



            } catch (JSONException e) {
                e.printStackTrace();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addnote(){
        List<String> list = new ArrayList<String>();
        list.add("EFS");
        list.add("CC");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

    }

    class BackgroundTask extends AsyncTask<String,Void,String> {
        String json_url;


        @Override
        protected void onPreExecute() {
            json_url = "http://10.0.3.2/set_recours.php";
        }

        @Override
        protected String doInBackground(String... voids) {

            try {
                URL url = new URL(json_url);


                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();


                httpURLConnection.setRequestMethod("POST");


                httpURLConnection.setDoOutput(true);


                httpURLConnection.setDoInput(true);


                OutputStream outputStream = httpURLConnection.getOutputStream();


                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));


                String data = URLEncoder.encode("recours","UTF-8")+"="+URLEncoder.encode(recours,"UTF-8")+"&"
                        +URLEncoder.encode("login_mat","UTF-8")+"="+URLEncoder.encode(login_mat,"UTF-8")+"&"
                        +URLEncoder.encode("matier","UTF-8")+"="+URLEncoder.encode(matier,"UTF-8")+"&"
                        +URLEncoder.encode("note","UTF-8")+"="+URLEncoder.encode(note,"UTF-8");


                bufferedWriter.write(data);

                bufferedWriter.flush();

                bufferedWriter.close();

                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";

                String line = "";

                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
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
            res=result;
            text_recours.setText("");
            confirmer();


        }
    }
public void confirmer(){

    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

    if(res.equals("true")){
        alertDialogBuilder.setMessage("-Votre Recours est bien envoyé\n" +
                "-Voulez vous effectuer un autre Recours?");
    }else if (res.equals("false")){
        alertDialogBuilder.setMessage("-Votre Recours n'est pas envoyé\n" +
                "-Voulez vous refaire l'opération");
    }
    alertDialogBuilder.setTitle("Réponse");
    alertDialogBuilder.setNegativeButton("NON",
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
    alertDialogBuilder.setPositiveButton("OUI",
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {




                }
            });
    alertDialogBuilder.show();
}

}

