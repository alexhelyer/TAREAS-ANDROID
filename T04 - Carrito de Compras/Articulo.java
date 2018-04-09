package com.example.alejandro.carritotenis;

import java.io.Serializable;

/**
 * Created by alejandro on 03/04/18.
 */

public class Articulo implements Serializable {

    private String titulo;
    private String subtitulo;
    private String descripcion;
    private double precio;
    private int imagen1;
    private int imagen2;


    public Articulo(String titulo, String subtitulo, String descripcion, double precio, int imagen1, int imagen2) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.descripcion = descripcion;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen1() {
        return imagen1;
    }

    public void setImagen1(int imagen1) {
        this.imagen1 = imagen1;
    }

    public int getImagen2() {
        return imagen2;
    }

    public void setImagen2(int imagen2) {
        this.imagen2 = imagen2;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
