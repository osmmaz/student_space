package com.example.oussama.des;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayMat extends AppCompatActivity {

    String json_string,login_mat,login_pass;
    JSONObject jsonObject;
    JSONArray jsonArray;
    MatierAdapter matierAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mat);
        login_mat = getIntent().getExtras().getString("mat");
        login_pass = getIntent().getExtras().getString("pass");
        listView = (ListView) findViewById(R.id.listview);

        matierAdapter = new MatierAdapter(this,R.layout.row_layout_mat);
        listView.setAdapter(matierAdapter);

        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String matier,cc,exm,rfs,moy;
            while (count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                matier = JO.getString("nom_matier");
                cc = JO.getString("note_cc");
                exm = JO.getString("note_exam");
                rfs = JO.getString("note_ratt");
                moy = JO.getString("moy_mat");
                if (cc.equals("null")){
                    cc = "*";
                }
                if (exm.equals("null")){
                    exm = "*";
                }
                if (rfs.equals("null")){
                    rfs = "*";
                }
                if (moy.equals("null")){
                    moy = "*";
                }

                Matiers matiers = new Matiers(matier,cc,exm,rfs,moy);
                matierAdapter.add(matiers);

                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void home(View view){
        finish();
    }
    public void ref(View view){
        Intent intent1 = new Intent(getApplicationContext(), Main3Activity.class);

        intent1.putExtra("matier","matier");
        intent1.putExtra("mat",login_mat);
        intent1.putExtra("pass",login_pass);

        startActivity(intent1);

        finish();
    }
}
