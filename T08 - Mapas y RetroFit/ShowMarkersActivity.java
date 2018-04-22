package com.alex.helyer.mapsretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowMarkersActivity extends AppCompatActivity implements Callback<ResponseReporte> {

    RecyclerView listaReportes;

    List<Reporte> arrayReportes;

    //Reporte que recibe
    Reporte reporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_markers);
        setTitle("Mis Marcadores");

        reporte = (Reporte) getIntent().getSerializableExtra("REPORTE_KEY");

        listaReportes = findViewById(R.id.listaReportes);

        listaReportes.setHasFixedSize(true);

        arrayReportes = new ArrayList<>();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        listaReportes.setLayoutManager(llm);

        printReportes();

        listaReportes.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildLayoutPosition(child);


                //Toast.makeText(ShowMarkersActivity.this, ""+arrayReportes.get(position).getLatitud(), Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent();
                intent1.putExtra("SETMARKER_KEY", arrayReportes.get(position));
                setResult(101, intent1);

                finish();

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                int x = rv.getChildCount();

                Toast.makeText(ShowMarkersActivity.this, "Click:"+x, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                Toast.makeText(ShowMarkersActivity.this, "onRequestDisallowInterceptTouchEvent", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void printReportes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndPointInterface service = retrofit.create(EndPointInterface.class);

        Call<ResponseReporte> reportes = service.verReportes(reporte.getLatitud()+"",reporte.getLongitud()+"");


        reportes.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseReporte> call, Response<ResponseReporte> response) {

        ResponseReporte responseReporte = response.body();

        List<Reporte> misReportes = responseReporte.getReportes();
        arrayReportes.clear();
        for (Reporte reporte:misReportes) {
            arrayReportes.add(reporte);
        }
        ReporteAdapter reporteAdapter = new ReporteAdapter(arrayReportes);

        listaReportes.setAdapter(reporteAdapter);
    }

    @Override
    public void onFailure(Call<ResponseReporte> call, Throwable t) {
        Toast.makeText(this, "No se puedieron cargar los datos", Toast.LENGTH_SHORT).show();

    }
}
