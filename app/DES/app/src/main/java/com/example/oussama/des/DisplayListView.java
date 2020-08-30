package com.example.oussama.des;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    EtudiantAdapter etudiantAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView = (ListView) findViewById(R.id.listview);

        etudiantAdapter = new EtudiantAdapter(this,R.layout.row_layout);
        listView.setAdapter(etudiantAdapter);

        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String mat,name,prename,email;
            while (count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                mat = JO.getString("n_inscription");
                name = JO.getString("nom_etud_fr");
                prename = JO.getString("prenom_etud_fr");
                email =   JO.getString("email");
                Etudiants etudiants = new Etudiants(mat,name,prename,email);
                etudiantAdapter.add(etudiants);

                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void home(View view){
        finish();
    }
}
