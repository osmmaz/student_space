package com.example.oussama.des;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Planning extends AppCompatActivity {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String json_string,login_mat,login_pass,type,matier,salle;
    int jour,deb;
    Intent i1;
    TextView cs[][] = new TextView[7][7];
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        i1 = getIntent();
        tv = (TextView) findViewById(R.id.c15);
        /*
        tv = (TextView) findViewById(R.id.c15);
        tv.setText(json_string);*/

        cs[1][1] = (TextView) findViewById(R.id.c11);
        cs[1][2] = (TextView) findViewById(R.id.c12);
        cs[1][3] = (TextView) findViewById(R.id.c13);
        cs[1][4] = (TextView) findViewById(R.id.c14);
        cs[1][5] = (TextView) findViewById(R.id.c15);
        cs[1][6] = (TextView) findViewById(R.id.c16);
        cs[2][1] = (TextView) findViewById(R.id.c21);
        cs[2][2] = (TextView) findViewById(R.id.c22);
        cs[2][3] = (TextView) findViewById(R.id.c23);
        cs[2][4] = (TextView) findViewById(R.id.c24);
        cs[2][5] = (TextView) findViewById(R.id.c25);
        cs[2][6] = (TextView) findViewById(R.id.c26);
        cs[3][1] = (TextView) findViewById(R.id.c31);
        cs[3][2] = (TextView) findViewById(R.id.c32);
        cs[3][3] = (TextView) findViewById(R.id.c33);
        cs[3][4] = (TextView) findViewById(R.id.c34);
        cs[3][5] = (TextView) findViewById(R.id.c35);
        cs[3][6] = (TextView) findViewById(R.id.c36);
        cs[4][1] = (TextView) findViewById(R.id.c41);
        cs[4][2] = (TextView) findViewById(R.id.c42);
        cs[4][3] = (TextView) findViewById(R.id.c43);
        cs[4][4] = (TextView) findViewById(R.id.c44);
        cs[4][5] = (TextView) findViewById(R.id.c45);
        cs[4][6] = (TextView) findViewById(R.id.c46);
        cs[5][1] = (TextView) findViewById(R.id.c51);
        cs[5][2] = (TextView) findViewById(R.id.c52);
        cs[5][3] = (TextView) findViewById(R.id.c53);
        cs[5][4] = (TextView) findViewById(R.id.c54);
        cs[5][5] = (TextView) findViewById(R.id.c55);
        cs[5][6] = (TextView) findViewById(R.id.c56);
        cs[6][1] = (TextView) findViewById(R.id.c61);
        cs[6][2] = (TextView) findViewById(R.id.c62);
        cs[6][3] = (TextView) findViewById(R.id.c63);
        cs[6][4] = (TextView) findViewById(R.id.c64);
        cs[6][5] = (TextView) findViewById(R.id.c65);
        cs[6][6] = (TextView) findViewById(R.id.c66);

        login_mat = i1.getExtras().getString("mat");
        login_pass = i1.getExtras().getString("pass");
        if(i1.hasExtra("json_data")) {
            json_string = i1.getExtras().getString("json_data");
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
                type = JO.getString("type");
                jour = JO.getInt("jour");
                deb = JO.getInt("deb");
                salle = JO.getString("salle");
                matier = JO.getString("matier");
                if (type.equals("TD")||type.equals("TP")){cs[jour][deb].setBackgroundColor(Color.YELLOW);}
                else if (type.equals("Cours")){cs[jour][deb].setBackgroundColor(Color.BLUE);}
                cs[jour][deb].setText(type+": "+matier+"/"+"salle: "+salle);

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

        intent1.putExtra("cours","cours");
        intent1.putExtra("mat",login_mat);
        intent1.putExtra("pass",login_pass);

        startActivity(intent1);

        finish();
    }
}
