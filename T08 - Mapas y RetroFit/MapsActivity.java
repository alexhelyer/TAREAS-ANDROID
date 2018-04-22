package com.alex.helyer.mapsretrofit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    boolean isMapReady;

    //Elementos
    FloatingActionButton btnMarkers;
    FloatingActionButton btnAdd;
    FloatingActionButton btnLocation;

    //Localizacion
    Reporte thisReporte;
    Marker marker;
    LocationManager locationManager;
    Localizacion localizacion;

    //Constant - PERMISSION
    static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 43;
    public static final int MY_REQUEST_CODE = 100;


    boolean onlyMarker;

    Reporte reporte1;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        isMapReady = false;
        onlyMarker = false;

        btnMarkers = findViewById(R.id.btnMyMarkers);
        btnAdd = findViewById(R.id.btnAddMarker);
        btnLocation = findViewById(R.id.btnMiLocation);

        btnMarkers.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnLocation.setOnClickListener(this);

        thisReporte = new Reporte();
        reporte1 = new Reporte();

        //place.setLocation(new LatLng(-34, 151));
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        localizacion = new Localizacion();
        localizacion.setReporte(thisReporte);

        requestGpsPermission();

    }

    @Override
    protected void onResume() {
        checkGpsPermission();
        checkPrintMarkers();
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, localizacion);
                }
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mexico = new LatLng(19.3910038, -99.2836977);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mexico, 5.0f));
        marker = mMap.addMarker(new MarkerOptions()
                .position(mexico)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));

        localizacion.setMarker(marker);

        isMapReady = true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnMyMarkers:
                if(isMapReady) {
                    if(thisReporte.getLatitud()!=null && thisReporte.getLongitud()!=null) {
                        Intent intent1 = new Intent(this, ShowMarkersActivity.class);
                        intent1.putExtra("REPORTE_KEY", thisReporte);
                        startActivityForResult(intent1, MY_REQUEST_CODE);
                    } else {
                        Toast.makeText(this, "No se ha detectado ubicación", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "El mapa se esta cargando", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnAddMarker:
                if(isMapReady) {
                    if(thisReporte.getLatitud()!=null && thisReporte.getLongitud()!=null) {
                        onlyMarker = false;
                        Intent intent2 = new Intent(this, AddReporteActivity.class);
                        intent2.putExtra("REPORTE_KEY", thisReporte);
                        startActivity(intent2);
                    } else {
                        Toast.makeText(this, "No se ha detectado ubicación", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "El mapa se esta cargando", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnMiLocation:
                if(isMapReady) {
                    if(thisReporte.getLatitud()!=null && thisReporte.getLongitud()!=null) {
                        LatLng getLocation = new LatLng(thisReporte.getLatitud(), thisReporte.getLongitud());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(getLocation, 17.5f));
                        printReportes();
                    } else {
                        Toast.makeText(this, "Obteniendo ubicación...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "El mapa se esta cargando", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestGpsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != 0)
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
    }

    public void checkGpsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==0) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, localizacion);
        }
    }

    public void printReportes() {

        mMap.clear();

        marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(thisReporte.getLatitud(), thisReporte.getLongitud()))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EndPointInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EndPointInterface service = retrofit.create(EndPointInterface.class);

        Call<ResponseReporte> reportes = service.verReportes(thisReporte.getLatitud()+"",thisReporte.getLongitud()+"");


        reportes.enqueue(new Callback<ResponseReporte>() {
            @Override
            public void onResponse(Call<ResponseReporte> call, Response<ResponseReporte> response) {

                ResponseReporte responseReporte = response.body();
                List<Reporte> misReportes = responseReporte.getReportes();

                for (Reporte reporte:misReportes) {
                    if (isMapReady && !onlyMarker) {
                        mMap.addMarker(new MarkerOptions()
                                .title(reporte.getHashtag())
                                .position(new LatLng(reporte.getLatitud(), reporte.getLongitud())));
                    }
                }

                if (isMapReady && onlyMarker) {
                    LatLng thisPosition = new LatLng(reporte1.getLatitud(), reporte1.getLongitud());
                    mMap.addMarker(new MarkerOptions()
                            .title(reporte1.getHashtag())
                            .position(thisPosition));
                }

            }

            @Override
            public void onFailure(Call<ResponseReporte> call, Throwable t) {

            }
        });
    }

    public void checkPrintMarkers() {
        if (isMapReady && thisReporte.getLatitud()!=null && thisReporte.getLongitud()!=null)
            printReportes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==MY_REQUEST_CODE) {
            switch (resultCode) {
                case 101:
                    reporte1 = (Reporte) data.getSerializableExtra("SETMARKER_KEY");
                    if (isMapReady) {
                        LatLng thisPosition = new LatLng(reporte1.getLatitud(), reporte1.getLongitud());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(thisPosition, 17.5f));
                        onlyMarker = true;
                    }

                    break;
            }
        }
    }
}
