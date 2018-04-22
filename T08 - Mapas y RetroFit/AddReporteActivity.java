package com.alex.helyer.mapsretrofit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddReporteActivity extends AppCompatActivity implements View.OnClickListener {

    EditText hashtag;
    EditText comentario;
    Button agregar;

    Reporte reporte;

    public static final int MY_REQUEST_KEY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reporte);

        reporte = (Reporte) getIntent().getSerializableExtra("REPORTE_KEY");

        hashtag = findViewById(R.id.inputHashtag);
        comentario = findViewById(R.id.inputComentario);
        agregar = findViewById(R.id.btnGuardar);

        agregar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardar:

                String txtHashtag = hashtag.getText().toString();
                String txtComentario = hashtag.getText().toString();

                if (!txtHashtag.isEmpty() && !txtComentario.isEmpty()) {
                    insertReporte(txtHashtag,txtComentario);
                } else {
                    Toast.makeText(this, "¡Por favor, llena todos los campos!", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    public void insertReporte(String hashtag, String comentario) {

        final ProgressDialog dialog = ProgressDialog.show(this, "Subiendo comentario",
                "Espere un momento....",false, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointInterface.URL)
                .build();

        EndPointInterface service = retrofit.create(EndPointInterface.class);

        Call<Void> request = service.insertarReporte(hashtag, comentario, reporte.getLatitud()+"", reporte.getLongitud()+"");

        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddReporteActivity.this, "Se insertó un nuevo comentario", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddReporteActivity.this, "¡Error al insertar comentario!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
