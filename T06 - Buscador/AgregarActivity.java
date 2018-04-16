package com.alex.helyer.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarActivity extends AppCompatActivity {

    //
    EditText nombre;
    EditText puesto;
    EditText correo;

    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        setTitle("Añadir Empleado");

        //
        nombre = findViewById(R.id.inputNombre);
        puesto = findViewById(R.id.inputPuesto);
        correo = findViewById(R.id.inputCorreo);

        agregar = findViewById(R.id.btnGuardar);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNombre = nombre.getText().toString();
                String txtPuesto = puesto.getText().toString();
                String txtCorreo = correo.getText().toString();



                if (!txtNombre.isEmpty() && !txtPuesto.isEmpty() && !txtCorreo.isEmpty()) {

                    if (txtCorreo.contains("@") && txtCorreo.contains(".")) {
                        Empleado empleado = new Empleado(txtNombre,txtPuesto,txtCorreo);

                        Intent intent = new Intent();
                        intent.putExtra("EMPKEY", empleado);
                        setResult(101, intent);
                        finish();
                    } else {
                        Toast.makeText(AgregarActivity.this, "Ingresa un correo válido", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(AgregarActivity.this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
