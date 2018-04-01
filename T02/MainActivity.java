package com.alex.helyer.formas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre;
    EditText empresa;
    EditText edad;

    Button mostrarDatos;

    EditText val1;
    EditText val2;

    Button sumarValores;
    Button multiplicarValores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.etNombre);
        empresa = findViewById(R.id.etEmpresa);
        edad = findViewById(R.id.etEdad);

        mostrarDatos = findViewById(R.id.btnDatos);

        val1 = findViewById(R.id.etVal1);
        val2 = findViewById(R.id.etVal2);

        sumarValores = findViewById(R.id.btnSumar);
        multiplicarValores = findViewById(R.id.btnMultiplicar);

        mostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datos = "Nombre:" + nombre.getText().toString() + "\n" + "Empresa:" + empresa.getText().toString() + "\n" + "Edad:" + edad.getText().toString();
                Toast.makeText(MainActivity.this, datos, Toast.LENGTH_SHORT).show();
            }
        });

        sumarValores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = val1.getText().toString();
                String value2 = val2.getText().toString();
                if ( !value1.isEmpty() && !value2.isEmpty() )
                    Toast.makeText(MainActivity.this, "La suma es:"+(Integer.parseInt(value1) + Integer.parseInt(value2) ), Toast.LENGTH_SHORT).show();
            }
        });
        multiplicarValores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value1 = val1.getText().toString();
                String value2 = val2.getText().toString();
                if ( !value1.isEmpty() && !value2.isEmpty() )
                    Toast.makeText(MainActivity.this, "La multipicación es:"+(Integer.parseInt(value1) * Integer.parseInt(value2) ), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
