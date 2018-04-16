package com.alex.helyer.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoAdapter extends ArrayAdapter<Empleado> {

    int layout;
    List<Empleado> empleados;


    public EmpleadoAdapter(Context context, int layout, List<Empleado> empleados ) {
        super(context, layout, empleados);
        this.layout = layout;
        this.empleados = empleados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(layout,parent,false);

        TextView nombre = row.findViewById(R.id.textNombre);
        TextView puesto = row.findViewById(R.id.textPuesto);
        TextView correo = row.findViewById(R.id.textCorreo);

        Empleado empleado = empleados.get(position);

        nombre.setText(empleado.getNombre());
        puesto.setText(empleado.getPuesto());
        correo.setText("("+empleado.getCorreo()+")");

        return row;
    }
}
