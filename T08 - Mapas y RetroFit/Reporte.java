package com.alex.helyer.mapsretrofit;

import java.io.Serializable;

public class Reporte implements Serializable{

    Integer id_reporte;
    String hashtag;
    String comentario;
    Double longitud;
    Double latitud;
    Double distancia;

    public Reporte() {
    }

    public Reporte(Integer id_reporte, String hashtag, String comentario, Double longitud, Double latitud, Double distancia) {
        this.id_reporte = id_reporte;
        this.hashtag = hashtag;
        this.comentario = comentario;
        this.longitud = longitud;
        this.latitud = latitud;
        this.distancia = distancia;
    }

    public Integer getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Integer id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

}
