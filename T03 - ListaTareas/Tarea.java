package com.alex.helyer.adaptador;

import java.io.Serializable;

public class Tarea implements Serializable {

    String colorFecha;
    String titulo;
    String descripcion;
    String fecha;
    int categoria;

    public Tarea(String colorFecha, String titulo, String descripcion, String fecha, int categoria) {
        this.colorFecha = colorFecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public String getColorFecha() {
        return colorFecha;
    }

    public void setColorFecha(String colorFecha) {
        this.colorFecha = colorFecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
