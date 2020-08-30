package com.example.oussama.des;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oussama on 06/04/17.
 */


public class EtudiantAdapter extends ArrayAdapter {
    List list = new ArrayList();


    public EtudiantAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Etudiants object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();

    }



    @Override
    public Object getItem(int position) {
        return list.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        EtudiantHolder etudiantHolder;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            etudiantHolder = new EtudiantHolder();
            etudiantHolder.tx_mat =(TextView) row.findViewById(R.id.tx_mat);
            etudiantHolder.tx_name =(TextView) row.findViewById(R.id.tx_name);
            etudiantHolder.tx_prename =(TextView) row.findViewById(R.id.tx_prename);
            etudiantHolder.tx_email = (TextView) row.findViewById(R.id.tx_email);
            row.setTag(etudiantHolder);



        }

        else
        {
            etudiantHolder = (EtudiantHolder) row.getTag();

        }

        Etudiants etudiants = (Etudiants) this.getItem(position);

        etudiantHolder.tx_mat.setText(etudiants.getMat());
        etudiantHolder.tx_name.setText(etudiants.getName());
        etudiantHolder.tx_prename.setText(etudiants.getPrename());
        etudiantHolder.tx_email.setText(etudiants.getEmail());
        return row;

    }

    static class EtudiantHolder

    {
        TextView tx_mat,tx_name,tx_prename,tx_email;
    }
}