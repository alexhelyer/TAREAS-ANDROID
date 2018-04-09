package com.example.alejandro.simuladorfutbol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView header;
    ListView lista_partidos;

    ArrayList<Partido> arrayPartidos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        header = findViewById(R.id.imgHeader);
        lista_partidos = findViewById(R.id.listPartidos);

        header.setImageResource(R.drawable.ligamx);

        arrayPartidos = new ArrayList<>();

        arrayPartidos.add(new Partido(R.drawable.puebla,"Puebla", R.drawable.pachuca, "Pachuca"));
        arrayPartidos.add(new Partido(R.drawable.tijuana,"Club Tijuana", R.drawable.atlas, "Atlas"));
        arrayPartidos.add(new Partido(R.drawable.cruzazul,"Cruz Azul", R.drawable.lobos, "Lobos BUAP"));
        arrayPartidos.add(new Partido(R.drawable.monterrey,"Monterrey", R.drawable.pumas, "Pumas"));
        arrayPartidos.add(new Partido(R.drawable.leon,"León", R.drawable.morelia, "Morelia"));
        arrayPartidos.add(new Partido(R.drawable.necaxa,"Necaxa", R.drawable.america, "América"));
        arrayPartidos.add(new Partido(R.drawable.chivas,"Chivas", R.drawable.veracruz, "Veracruz"));
        arrayPartidos.add(new Partido(R.drawable.toluca,"Toluca", R.drawable.tigres, "Tigres"));
        arrayPartidos.add(new Partido(R.drawable.santos,"Santos", R.drawable.queretaro, "Club Querétaro"));

        ArrayAdapter arrayAdapter = new PartidoAdapter();

        lista_partidos.setAdapter(arrayAdapter);

        lista_partidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, "ID:"+i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, PartidoActivity.class);
                intent.putExtra("partido", arrayPartidos.get(i));
                startActivity(intent);
            }
        });




    }

    public class PartidoAdapter extends ArrayAdapter<Partido> {

        public PartidoAdapter() {
            super(MainActivity.this, R.layout.partido, arrayPartidos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.partido, parent, false);

            ImageView imageLocal = view.findViewById(R.id.imgLocal);
            TextView textLocal = view.findViewById(R.id.nombreLocal);

            ImageView imageVisitante = view.findViewById(R.id.imgVisitante);
            TextView textVisitante = view.findViewById(R.id.nombreVisitante);

            Partido partido = arrayPartidos.get(position);

            imageLocal.setImageResource(partido.getImgLocal());
            textLocal.setText(partido.getNomLocal());

            imageVisitante.setImageResource(partido.getImgVisitante());
            textVisitante.setText(partido.getNomVisitante());

            return view;
        }
    }
}
