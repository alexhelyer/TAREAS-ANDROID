package com.example.alejandro.simuladorfutbol;

import java.io.Serializable;

/**
 * Created by alejandro on 06/04/18.
 */

public class Partido implements Serializable {

    int imgLocal;
    String nomLocal;
    int imgVisitante;
    String nomVisitante;

    public Partido(int imgLocal, String nomLocal, int imgVisitante, String nomVisitante) {
        this.imgLocal = imgLocal;
        this.nomLocal = nomLocal;
        this.imgVisitante = imgVisitante;
        this.nomVisitante = nomVisitante;
    }

    public int getImgLocal() {
        return imgLocal;
    }

    public void setImgLocal(int imgLocal) {
        this.imgLocal = imgLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public int getImgVisitante() {
        return imgVisitante;
    }

    public void setImgVisitante(int imgVisitante) {
        this.imgVisitante = imgVisitante;
    }

    public String getNomVisitante() {
        return nomVisitante;
    }

    public void setNomVisitante(String nomVisitante) {
        this.nomVisitante = nomVisitante;
    }

}
