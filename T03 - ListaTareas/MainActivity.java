package com.alex.helyer.adaptador;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int OPCION_UNO = Menu.FIRST;
    public static final int OPCION_DOS = Menu.FIRST+1;
    public static final int OPCION_TRES = Menu.FIRST+2;
    public static final String[] MESES = {"ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"};

    //EditText nuevaTarea;
    //Button  agregarTarea;

    ListView lista_pendientes;
    ListView lista_completados;


    ArrayAdapter<Tarea> adapterPendientes;
    ArrayAdapter<Tarea> adapterCompletados;


    ArrayList<Tarea> array_pendientes = new ArrayList<>();
    ArrayList<Tarea> array_completados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tus tareas");

        array_pendientes.add(new Tarea("#666666", "Despensa", "Ir por la despensa el fin de semana", "20180308", R.drawable.ic_home_black_24dp));
        array_pendientes.add(new Tarea("#666666", "Colegiatura", "Pagar la colegiatura", "20180402", R.drawable.ic_school_black_24dp));
        array_pendientes.add(new Tarea("#666666", "Visa", "Realizar la cita para el tramite de visa", "20180314", R.drawable.ic_work_black_24dp));

        array_completados.add(new Tarea("#666666", "Dormir", "Dormir las ocho horas diarias para estar sano", "20180408", R.drawable.ic_local_hospital_black_24dp));
        array_completados.add(new Tarea("#666666", "Vuelo", "Comprar boletos de vuelo a Guadalajara", "20180408", R.drawable.ic_work_black_24dp));

        lista_pendientes = findViewById(R.id.lvPendientes);
        lista_completados = findViewById(R.id.lvCompletados);

        adapterPendientes = new TareaPAdapter();
        adapterCompletados = new TareaCAdapter();

        lista_pendientes.setAdapter(adapterPendientes);
        lista_pendientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mostrar(array_pendientes, position);
            }
        });

        lista_completados.setAdapter(adapterCompletados);
        lista_completados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mostrar(array_completados, position);
            }
        });

        registerForContextMenu(lista_pendientes);

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
            case R.id.navigation_agregar:
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100) {
            if(resultCode==101) {
                Tarea tarea = (Tarea) data.getSerializableExtra("TAREA");
                array_pendientes.add(tarea);
                adapterPendientes.notifyDataSetChanged();
            }
            else if(resultCode==102) {
                Tarea tarea = (Tarea) data.getSerializableExtra("TAREA");
                int position = data.getIntExtra("POSITION", 0);
                //Toast.makeText(this, "GET:"+position, Toast.LENGTH_SHORT).show();
                array_pendientes.get(position).setColorFecha(tarea.getColorFecha());
                array_pendientes.get(position).setTitulo(tarea.getTitulo());
                array_pendientes.get(position).setDescripcion(tarea.getDescripcion());
                array_pendientes.get(position).setFecha(tarea.getFecha());
                array_pendientes.get(position).setCategoria(tarea.getCategoria());
                adapterPendientes.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu, menu);
        menu.add(Menu.NONE, OPCION_UNO,Menu.NONE,"Completar");
        menu.add(Menu.NONE,OPCION_DOS,Menu.NONE,"Editar");
        menu.add(Menu.NONE,OPCION_TRES,Menu.NONE,"Eliminar");
        super.onCreateContextMenu(menu, v, menuInfo);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo elemento = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case OPCION_UNO:
                array_completados.add(array_pendientes.get(elemento.position));
                adapterCompletados.notifyDataSetChanged();

                array_pendientes.remove(elemento.position);
                adapterPendientes.notifyDataSetChanged();
                break;
            case OPCION_DOS:
                Intent intent = new Intent(MainActivity.this, EditarActivity.class);
                intent.putExtra("tarea", array_pendientes.get(elemento.position));
                intent.putExtra("position", elemento.position);
                startActivityForResult(intent, 100);
                break;
            case OPCION_TRES:
                array_pendientes.remove(elemento.position);
                adapterPendientes.notifyDataSetChanged();
                break;
        }

        //Toast.makeText(this, ""+elemento.position, Toast.LENGTH_SHORT).show();

        return super.onContextItemSelected(item);
    }

    private void Editar(int position) {

        LayoutInflater inflater = LayoutInflater.from(this);
        View editarView = inflater.inflate(R.layout.row,null);

        new AlertDialog.Builder(this)
                .setTitle("Mostrar")
                .setView(editarView)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }

    private void Mostrar(ArrayList<Tarea> tareas, int position) {

        Tarea tarea = tareas.get(position);

        LayoutInflater inflater = LayoutInflater.from(this);
        View editarView = inflater.inflate(R.layout.mostrar_tarea,null);

        TextView titulo = editarView.findViewById(R.id.txtTitulo);
        TextView descripcion = editarView.findViewById(R.id.txtDescripcion);
        TextView fecha = editarView.findViewById(R.id.txtFecha);
        ImageView icono = editarView.findViewById(R.id.imgIcono);

        titulo.setText(tarea.getTitulo());
        descripcion.setText(tarea.getDescripcion());
        int numberToDate = Integer.parseInt(tarea.getFecha());
        int year = numberToDate/10000;
        int month = (numberToDate - year*10000)/100;
        int day = numberToDate - year*10000 - month*100;
        fecha.setText(day+" "+MESES[month]+", "+year);
        icono.setImageResource(tarea.getCategoria());

        new AlertDialog.Builder(this)
                .setTitle("Mostrar")
                .setView(editarView)
                .setPositiveButton("Ok", null)
                .show();

    }

    public class TareaPAdapter extends ArrayAdapter<Tarea> {

        public TareaPAdapter() {
            super(MainActivity.this, R.layout.row, array_pendientes);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row, parent, false);

            Tarea tarea = array_pendientes.get(position);

            TextView titulo = view.findViewById(R.id.txtTitulo);
            TextView descripcion = view.findViewById(R.id.txtDescripcion);
            TextView fecha = view.findViewById(R.id.txtFecha);
            ImageView icono = view.findViewById(R.id.imgIcono);
            LinearLayout colorFecha = view.findViewById(R.id.color);

            String limitarDescripcion = tarea.getDescripcion();
            if(limitarDescripcion.length()>30)
                limitarDescripcion = limitarDescripcion.substring(0,29) + "...";

            titulo.setText(tarea.getTitulo());
            descripcion.setText(limitarDescripcion);

            int numberToDate = Integer.parseInt(tarea.getFecha());
            int year = numberToDate/10000;
            int month = (numberToDate - year*10000)/100;
            int day = numberToDate - year*10000 - month*100;
            fecha.setText(day+" "+MESES[month]+", "+year);
            icono.setImageResource(tarea.getCategoria());

            Calendar date = Calendar.getInstance();

            Date date1 = new Date(year, month, day);
            Date date2 = new Date(date.get(Calendar.YEAR), date.get(Calendar.MONTH),date.get(Calendar.DATE));

            if ( ( ( date1.getTime() - date2.getTime() )/86400000 ) > 7 ) {
                colorFecha.setBackgroundColor(Color.parseColor("#27AE60"));
            }
            else if ( ( ( date1.getTime() - date2.getTime() )/86400000 ) > 2 ) {
                colorFecha.setBackgroundColor(Color.parseColor("#F4D03F"));
            } else {
                colorFecha.setBackgroundColor(Color.parseColor("#E74C3C"));
            }

            return view;
        }
    }

    public class TareaCAdapter extends ArrayAdapter<Tarea> {

        public TareaCAdapter() {
            super(MainActivity.this, R.layout.row, array_completados);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row, parent, false);

            Tarea tarea = array_completados.get(position);

            TextView titulo = view.findViewById(R.id.txtTitulo);
            TextView descripcion = view.findViewById(R.id.txtDescripcion);
            TextView fecha = view.findViewById(R.id.txtFecha);
            ImageView icono = view.findViewById(R.id.imgIcono);
            LinearLayout colorFecha = view.findViewById(R.id.color);

            String limitarDescripcion = tarea.getDescripcion();
            if(limitarDescripcion.length()>30)
                limitarDescripcion = limitarDescripcion.substring(0,29) + "...";

            titulo.setText(tarea.getTitulo());
            descripcion.setText(limitarDescripcion);
            int numberToDate = Integer.parseInt(tarea.getFecha());
            int year = numberToDate/10000;
            int month = (numberToDate - year*10000)/100;
            int day = numberToDate - year*10000 - month*100;
            fecha.setText(day+" "+MESES[month]+", "+year);
            icono.setImageResource(tarea.getCategoria());
            colorFecha.setBackgroundColor(Color.parseColor("#3498DB"));

            return view;
        }
    }
}
