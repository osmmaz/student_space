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

public class group extends AppCompatActivity {

    List<String> list2 = new ArrayList<String>();
    List<String> list = new ArrayList<String>();
    JSONObject jsonObject;
    JSONArray jsonArray;
    Spinner spinner;
    Intent i1;
    EditText text_group;
    String login_mat,json_string,group,group_id,demande,res;
    Button valider;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        valider = (Button) findViewById(R.id.valider);
        text_group = (EditText) findViewById(R.id.text_ch_group);
        spinner =(Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.textView);
        i1 = getIntent();
        if(i1.hasExtra("json_data")) {
            json_string = i1.getExtras().getString("json_data");

            add();
        }

    }

    public void valider(View view){
        demande = text_group.getText().toString();
        group = spinner.getSelectedItem().toString();
        group_id = list2.get(list.indexOf(group));
        login_mat = getIntent().getExtras().getString("login_mat");

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




        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String group_num,group_id;
            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                group_num = JO.getString("num_groupe");
                group_id = JO.getString("id_groupe");

                list.add(group_num);
                list2.add(group_id);
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


    class BackgroundTask extends AsyncTask<String,Void,String> {
        String json_url;


        @Override
        protected void onPreExecute() {
            json_url = "http://10.0.3.2/set_group.php";
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


                String data = URLEncoder.encode("demande","UTF-8")+"="+URLEncoder.encode(demande,"UTF-8")+"&"
                        +URLEncoder.encode("login_mat","UTF-8")+"="+URLEncoder.encode(login_mat,"UTF-8")+"&"
                        +URLEncoder.encode("group","UTF-8")+"="+URLEncoder.encode(group_id,"UTF-8");


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
            text_group.setText(result);
            confirmer();
        }
    }
    public void confirmer(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        if(res.equals("true")){
            alertDialogBuilder.setMessage("-Votre Demande est bien envoyé\n" +
                    "-Voulez vous effectuer une autre Deamnde?");
        }else if (res.equals("false")){
            alertDialogBuilder.setMessage("-Votre Demande n'est pas envoyé\n" +
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
