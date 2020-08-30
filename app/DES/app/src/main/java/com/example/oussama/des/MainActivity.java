package com.example.oussama.des;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String login_mat,login_pass,json_string,json_url,groupe,spe,sec,nom,prenom;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView tx_nom,tx_prenom,tx_sec,tx_spe,grp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tx_nom = (TextView) findViewById(R.id.nom);
        tx_prenom = (TextView) findViewById(R.id.prenom);
        tx_sec = (TextView) findViewById(R.id.sec);
        tx_spe = (TextView) findViewById(R.id.spe);
        grp = (TextView) findViewById(R.id.grp);
        login_mat = getIntent().getExtras().getString("mat");
        login_pass = getIntent().getExtras().getString("pass");

        new BackgroungTask().execute();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_dcnx) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accueil) {

        } else if (id == R.id.nav_profile) {
            Toinfo();
        } else if (id == R.id.nav_cours) {
            ToCours();
        } else if (id == R.id.nav_examen) {

        } else if (id == R.id.nav_consultation) {
            ToMatier();
        } else if (id == R.id.nav_deliberation) {

        } else if (id == R.id.nav_recours) {
            ToRecours();
        } else if (id == R.id.nav_change) {
            ToGroup();
        }else if (id == R.id.nav_dec) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ///////////Logout///////////////
    public void logout(){
        final android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Deconnection");
        alertDialogBuilder.setMessage("Souhaitez-vous vraiment Vous Déconnecte?");
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
                        Intent i;

                        i = new Intent(getApplicationContext(),LoginActivity.class);

                        startActivity(i);

                        finish();

                    }
                });
        alertDialogBuilder.show();

    }

    public void Toinfo(){
        Intent intent1 = new Intent(getApplicationContext(), Main2Activity.class);

        intent1.putExtra("yes","true");
        intent1.putExtra("mat",login_mat);
        intent1.putExtra("pass",login_pass);

        startActivity(intent1);
    }

    public void ToRecours(){
        Intent i1;
        i1 = new Intent(getApplicationContext(),Main3Activity.class);
        i1.putExtra("recours","recours");
        i1.putExtra("mat",login_mat);
        i1.putExtra("pass",login_pass);
        startActivity(i1);

    }
    public void ToMatier(){
        Intent intent1 = new Intent(getApplicationContext(), Main3Activity.class);

        intent1.putExtra("matier","matier");
        intent1.putExtra("mat",login_mat);
        intent1.putExtra("pass",login_pass);

        startActivity(intent1);

    }
    public void ToGroup(){
        Intent i1;
        i1 = new Intent(getApplicationContext(),Main3Activity.class);
        i1.putExtra("group","group");
        i1.putExtra("mat",login_mat);
        i1.putExtra("pass",login_pass);
        startActivity(i1);

    }
    public void ToCours(){
        Intent i1;
        i1 = new Intent(getApplicationContext(),Main3Activity.class);
        i1.putExtra("cours","cours");
        i1.putExtra("mat",login_mat);
        i1.putExtra("pass",login_pass);
        startActivity(i1);

    }

    class BackgroungTask extends AsyncTask<Void,Void,String>

    {

        String JSON_STRING;
        @Override
        protected void onPreExecute() {

                json_url = "http://10.0.3.2/get_spe.php";


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
          add();
        }
    }
    public void add() {





        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                nom = JO.getString("nom");
                prenom = JO.getString("prenom");
                groupe = JO.getString("groupe");
                spe = JO.getString("specialite");
                sec = JO.getString("section");
                tx_spe.setText(spe);
                tx_sec.setText(sec);
                tx_prenom.setText(prenom);
                tx_nom.setText(nom);
                grp.setText(groupe);

                count++;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
