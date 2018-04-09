package com.alex.helyer.adaptador;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    Spinner categoria;
    Button btnFecha;
    EditText titulo;
    EditText descripcion;
    Button agregar;

    final String[] MESES = {"ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"};
    final int[] ICONOS = {R.drawable.ic_home_black_24dp, R.drawable.ic_work_black_24dp, R.drawable.ic_school_black_24dp, R.drawable.ic_local_hospital_black_24dp};

    ArrayAdapter<String> spinnerAdapter;
    String[] spiners = {"CASA", "TRABAJO", "ESCUELA", "SALUD"};

    Tarea tarea = new Tarea("#000","","","",0);

    String buffer_fecha = "20180101";

    int obtenerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        setTitle("Editar Tarea");

        Tarea obtenerTarea = (Tarea) getIntent().getSerializableExtra("tarea");
        obtenerPosition = getIntent().getIntExtra("position",0);

        categoria = findViewById(R.id.inCategoria);
        btnFecha = findViewById(R.id.btnFecha);
        titulo = findViewById(R.id.inTitulo);
        descripcion = findViewById(R.id.inDescripcion);

        titulo.setText(obtenerTarea.getTitulo());
        descripcion.setText(obtenerTarea.getDescripcion());

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, spiners);
        categoria.setAdapter(spinnerAdapter);

        if (obtenerTarea.getCategoria()==ICONOS[3])
            categoria.setSelection(3);
        else if (obtenerTarea.getCategoria()==ICONOS[2])
            categoria.setSelection(2);
        else if (obtenerTarea.getCategoria()==ICONOS[1])
            categoria.setSelection(1);

        int numberToDate = Integer.parseInt(obtenerTarea.getFecha());
        int year = numberToDate/10000;
        int month = (numberToDate - year*10000)/100;
        int day = numberToDate - year*10000 - month*100;
        btnFecha.setText(day+" "+MESES[month]+", "+year);
        buffer_fecha = obtenerTarea.getFecha();

        categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tarea.setCategoria(ICONOS[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        agregar = findViewById(R.id.btnAgregar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!titulo.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty()) {
                    tarea.setTitulo(titulo.getText().toString());
                    tarea.setDescripcion(descripcion.getText().toString());
                    tarea.setFecha(buffer_fecha);
                    Intent intent = new Intent();
                    //intent.getExtras("Tarea", );
                    intent.putExtra("TAREA", tarea);
                    intent.putExtra("POSITION",obtenerPosition);
                    setResult(102, intent);
                    finish();
                } else {
                    Toast.makeText(EditarActivity.this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(EditarActivity.this);
                final View fechaView = inflater.inflate(R.layout.fecha,null);

                new AlertDialog.Builder(EditarActivity.this)
                        .setView(fechaView)
                        .setPositiveButton("Ok", null)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatePicker fechaPicker = fechaView.findViewById(R.id.pickerFecha);
                                String fecha = fechaPicker.getDayOfMonth()+" "+MESES[fechaPicker.getMonth()]+", "+fechaPicker.getYear();
                                btnFecha.setText(fecha);

                                int dateToNumber = fechaPicker.getYear()*10000 + fechaPicker.getMonth()*100 + fechaPicker.getDayOfMonth();
                                buffer_fecha = Integer.toString(dateToNumber);
                            }
                        })
                        .show();
            }
        });
    }

}
