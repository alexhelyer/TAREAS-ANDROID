package com.example.alejandro.simuladorfutbol;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PartidoActivity extends AppCompatActivity {

    TextView tiempoPartido;

    TextView textLocal;
    TextView textVisitante;

    TextView golesLocal;
    TextView golesVisitante;

    Button iniciarPartido;

    ListView listaGolesLocal;
    ArrayList<String> arrayGolesLocal;
    ArrayAdapter<String> GolesLocalAdapter;


    ListView listaGolesVisitante;
    ArrayList<String> arrayGolesVisitante;
    ArrayAdapter<String> GolesVisitanteAdapter;

    ListView listaGoles;
    ArrayList<String> arrayGoles;
    ArrayAdapter<String> GolesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_partido);
        getSupportActionBar().hide();

        tiempoPartido = findViewById(R.id.outTiempoPartido);

        golesLocal = findViewById(R.id.outGolesLocal);
        golesVisitante = findViewById(R.id.outGolesVisitante);

        iniciarPartido = findViewById(R.id.btnIniciarPartido);

        ImageView imageLocal = findViewById(R.id.imgLocal);
        textLocal = findViewById(R.id.nombreLocal);

        ImageView imageVisitante = findViewById(R.id.imgVisitante);
        textVisitante = findViewById(R.id.nombreVisitante);

        Partido partido = (Partido) getIntent().getSerializableExtra("partido");

        imageLocal.setImageResource(partido.getImgLocal());
        textLocal.setText(partido.getNomLocal());

        imageVisitante.setImageResource(partido.getImgVisitante());
        textVisitante.setText(partido.getNomVisitante());

        iniciarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarPartido.setVisibility(View.INVISIBLE);
                new IniciarPartido().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
            }
        });

        listaGolesLocal = findViewById(R.id.list_local);
        listaGolesVisitante = findViewById(R.id.list_visitante);
        listaGoles = findViewById(R.id.list_goles);

        arrayGolesLocal = new ArrayList<>();
        arrayGolesVisitante = new ArrayList<>();
        arrayGoles = new ArrayList<>();

        GolesLocalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayGolesLocal);
        GolesVisitanteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayGolesVisitante);
        GolesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayGoles);

        listaGolesLocal.setAdapter(GolesLocalAdapter);
        listaGolesVisitante.setAdapter(GolesVisitanteAdapter);
        listaGoles.setAdapter(GolesAdapter);

    }

    public String numberToTime (int time) {
        int min = time/60;
        int seg = time%60;

        String toMin = min<10?"0"+min:""+min;
        String toSeg = seg<10?"0"+seg:""+seg;

        String totime = toMin+":"+toSeg;

        return totime;
    }

    public int anotarGol() {

        int random = (int) (Math.random()*200);

        if(random==1)
            return 1;
        else if(random==2)
            return 2;

        return 0;
    }

    public class IniciarPartido extends AsyncTask<String, Integer, Bitmap> {

        int goles_local = 0;
        int goles_visitante = 0;

        @Override
        protected Bitmap doInBackground(String... strings) {

            for (int i=0; i<5401; i+=10) {
                publishProgress(i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int tiempo = values[0];
            String time = numberToTime(tiempo);
            tiempoPartido.setText(time);

            String min = time.substring(0,2);

            int gol = anotarGol();
            if(gol==1) {
                goles_local++;
                golesLocal.setText("("+goles_local+")");
                arrayGolesLocal.add("Gol: "+min+"''");
                GolesLocalAdapter.notifyDataSetChanged();
                arrayGoles.add("Gol: "+min+"'' : "+textLocal.getText().toString());
            } else if(gol==2) {
                goles_visitante++;
                golesVisitante.setText("("+goles_visitante+")");
                arrayGolesVisitante.add("Gol: "+min+"''");
                GolesVisitanteAdapter.notifyDataSetChanged();
                arrayGoles.add("Gol: "+min+"'' : "+textVisitante.getText().toString());
            }

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            LinearLayout layoutPartido = findViewById(R.id.layoutPartido);
            LinearLayout layoutMarcador = findViewById(R.id.layoutMarcador);

            GolesAdapter.notifyDataSetChanged();

            layoutPartido.setVisibility(View.GONE);
            layoutMarcador.setVisibility(View.VISIBLE);

        }
    }


}
