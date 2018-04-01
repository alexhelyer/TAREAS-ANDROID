package com.alex.helyer.adaptador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nuevaTarea;
    Button  agregarTarea;

    ListView mispendientes;
    ListView miscompletados;


    ArrayAdapter<String> adapterPendientes;
    ArrayAdapter<String> adapterCompletados;


    ArrayList<String> pendientes = new ArrayList<String>();
    ArrayList<String> completados = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pendientes.add("Ir por la despensa");
        pendientes.add("Pagar colegiatura");
        pendientes.add("Tramitar Visa");

        completados.add("Dormir bien");
        completados.add("Comprar boletos de vuelo");

        nuevaTarea = findViewById(R.id.etNuevaTarea);
        agregarTarea = findViewById(R.id.btnAgregar);

        mispendientes = findViewById(R.id.lvPendientes);
        miscompletados = findViewById(R.id.lvCompletados);

        adapterPendientes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pendientes);

        adapterCompletados = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, completados);

        mispendientes.setAdapter(adapterPendientes);

        miscompletados.setAdapter(adapterCompletados);

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nTarea = nuevaTarea.getText().toString();

                if ( !nTarea.isEmpty() ) {
                    pendientes.add(nTarea);
                    adapterPendientes.notifyDataSetChanged();
                }

            }
        });

        mispendientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                completados.add(pendientes.get(position));
                pendientes.remove(position);

                adapterPendientes.notifyDataSetChanged();
                adapterCompletados.notifyDataSetChanged();

                return false;
            }
        });




    }
}
