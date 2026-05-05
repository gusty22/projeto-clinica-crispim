package com.imepac.gustavoimepac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class PacienteAdapter extends ArrayAdapter<Paciente> {
    public PacienteAdapter(Context context, List<Paciente> pacientes) {
        super(context, 0, pacientes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Paciente p = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }
        TextView t1 = convertView.findViewById(android.R.id.text1);
        TextView t2 = convertView.findViewById(android.R.id.text2);

        t1.setText(p.getNome());
        t2.setText("CPF: " + p.getCpf());

        return convertView;
    }
}