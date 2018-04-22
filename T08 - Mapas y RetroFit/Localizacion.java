package com.alex.helyer.mapsretrofit;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by alejandro on 11/04/18.
 */

public class Localizacion implements LocationListener {

    private Reporte reporte;
    private Marker marker;

    public Localizacion() {
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("Location",location.getLatitude()+":"+location.getLongitude());

        if(reporte!=null) {
            reporte.setLatitud(location.getLatitude());
            reporte.setLongitud(location.getLongitude());
        }

        if(marker!=null) {
            marker.setPosition(new LatLng(reporte.getLatitud(), reporte.getLongitud()));
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
