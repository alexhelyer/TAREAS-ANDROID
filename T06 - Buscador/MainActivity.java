package com.alex.helyer.search;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText keyword;
    Button btnBuscar;

    ListView listaEmpleados;


    //
    List<Empleado> arrayEmpleados;
    EmpleadoAdapter empleadoAdapter;

    SQLiteDatabase db;
    Cursor cursor;

    public static final int MY_REQUEST_CODE = 100;
    public static final int OPCION_UNO = 1;
    public static final int OPCION_DOS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyword = findViewById(R.id.inputSearch);
        btnBuscar = findViewById(R.id.btnSearch);
        listaEmpleados = findViewById(R.id.listaEmpleados);

        arrayEmpleados = new ArrayList<>();
        empleadoAdapter = new EmpleadoAdapter(this, R.layout.empleado_row, arrayEmpleados);




        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String k = keyword.getText().toString();
                if (!k.isEmpty()) {
                    arrayEmpleados.clear();
                    cursor = db.rawQuery("SELECT _id, nombre, puesto, correo FROM empleados WHERE nombre LIKE '%"+k+"%' OR puesto LIKE '%"+k+"%' OR correo LIKE '%"+k+"%' ", null);
                    while (cursor.moveToNext()) {
                        arrayEmpleados.add(getEmpleadoDatos(cursor));
                    }
                    empleadoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Por favor escribe algo...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerForContextMenu(listaEmpleados);
        listaEmpleados.setAdapter(empleadoAdapter);

        //arrayEmpleados.add(new Empleado("Alex","Director","alex@gmail.com"));


    }

    public void pintarLista() {
        arrayEmpleados.clear();
        db = new DBHelper(this).getWritableDatabase();
        cursor = db.rawQuery("SELECT _id, nombre, puesto, correo FROM empleados", null);
        while (cursor.moveToNext()) {
            arrayEmpleados.add(getEmpleadoDatos(cursor));
        }
        empleadoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pintarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.navigation_nuevo:
                keyword.setText("");
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivityForResult(intent, MY_REQUEST_CODE);
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, OPCION_UNO,Menu.NONE,"Editar");
        menu.add(Menu.NONE, OPCION_DOS,Menu.NONE,"Borrar");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo elemento = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case OPCION_UNO:
                final Empleado empleado = arrayEmpleados.get(elemento.position);
                final int id = empleado.getId();

                LayoutInflater inflater = LayoutInflater.from(this);
                View view = inflater.inflate(R.layout.actualizar,null);

                final EditText nombre = view.findViewById(R.id.inputNombre);
                final EditText puesto = view.findViewById(R.id.inputPuesto);
                final EditText correo = view.findViewById(R.id.inputCorreo);

                nombre.setText(empleado.getNombre());
                puesto.setText(empleado.getPuesto());
                correo.setText(empleado.getCorreo());

                new AlertDialog.Builder(this)
                        .setTitle("Actualizar Empleado")
                        .setView(view)
                        .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String txtNombre = nombre.getText().toString();
                                String txtPuesto = puesto.getText().toString();
                                String txtCorreo = correo.getText().toString();
                                if (!txtNombre.isEmpty() && !txtPuesto.isEmpty() && !txtCorreo.isEmpty()) {
                                    Empleado uEmpleado = new Empleado(txtNombre,txtPuesto,txtCorreo);
                                    uEmpleado.setId(id);
                                    actualizarEmpleados(uEmpleado);
                                } else {
                                    Toast.makeText(MainActivity.this, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();

                break;
            case OPCION_DOS:
                borrarEmpleados(arrayEmpleados.get(elemento.position));
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==MY_REQUEST_CODE) {
            switch (resultCode) {
                case 101:
                    Empleado empleado = (Empleado) data.getSerializableExtra("EMPKEY");
                    setEmpleadoDatos(empleado);
                    break;
            }
        }
    }

    public Empleado getEmpleadoDatos(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
        String puesto = cursor.getString(cursor.getColumnIndex("puesto"));
        String correo = cursor.getString(cursor.getColumnIndex("correo"));

        Empleado empleado = new Empleado(nombre, puesto, correo);
        empleado.setId(id);

        return empleado;
    }

    public void setEmpleadoDatos(Empleado empleadoDatos) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", empleadoDatos.getNombre());
        cv.put("puesto", empleadoDatos.getPuesto());
        cv.put("correo", empleadoDatos.getCorreo());

        db.insert("empleados","nombre",cv);
    }

    public void borrarEmpleados(Empleado empleado) {
        String[] args = {String.valueOf(empleado.getId())};
        db.delete("empleados","_id=?",args);
        pintarLista();
    }

    public void actualizarEmpleados(Empleado empleado) {
        String[] args = {String.valueOf(empleado.getId())};

        ContentValues cv = new ContentValues();
        cv.put("nombre", empleado.getNombre());
        cv.put("puesto", empleado.getPuesto());
        cv.put("correo", empleado.getCorreo());

        db.update("empleados", cv, "_id=?",args);
        pintarLista();
    }
}
