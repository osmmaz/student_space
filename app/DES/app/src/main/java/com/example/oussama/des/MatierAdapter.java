package com.example.oussama.des;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oussama on 15/04/17.
 */

public class MatierAdapter extends ArrayAdapter {
    List list = new ArrayList();


    public MatierAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Matiers object) {
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
        MatierHolder matierHolder;

        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_mat,parent,false);
            matierHolder = new MatierHolder();

            matierHolder.tx_matier =(TextView) row.findViewById(R.id.tx_matier);
            matierHolder.tx_cc =(TextView) row.findViewById(R.id.tx_cc);
            matierHolder.tx_exm =(TextView) row.findViewById(R.id.tx_exm);
            matierHolder.tx_rfs =(TextView) row.findViewById(R.id.tx_rat);
            matierHolder.tx_moy =(TextView) row.findViewById(R.id.tx_moy);

            row.setTag(matierHolder);

        }
        else
        {
            matierHolder = (MatierHolder) row.getTag();

        }


        Matiers matiers = (Matiers) this.getItem(position);

        matierHolder.tx_matier.setText(matiers.getMatier());


        matierHolder.tx_cc.setText(matiers.getCc());
        matierHolder.tx_exm.setText(matiers.getExm());
        matierHolder.tx_rfs.setText(matiers.getRfs());
        matierHolder.tx_moy.setText(matiers.getMoy());


        if ( matierHolder.tx_cc.getText().equals("*")){matierHolder.tx_cc.setTextColor(Color.RED); }
        if (matierHolder.tx_exm.getText().equals("*")){matierHolder.tx_exm.setTextColor(Color.RED); }
        if (matierHolder.tx_rfs.getText().equals("*")){matierHolder.tx_rfs.setTextColor(Color.RED); }
        if (matierHolder.tx_moy.getText().equals("*")){matierHolder.tx_moy.setTextColor(Color.RED); }


        return row;
    }

    static class MatierHolder

    {
        TextView tx_matier,tx_cc,tx_exm,tx_rfs,tx_moy;

    }
}
