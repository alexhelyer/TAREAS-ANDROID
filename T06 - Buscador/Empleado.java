package com.alex.helyer.search;

import android.database.Cursor;

import java.io.Serializable;

public class Empleado implements Serializable {

    int id;
    String nombre;
    String puesto;
    String correo;

    public Empleado(String nombre, String puesto, String correo) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
