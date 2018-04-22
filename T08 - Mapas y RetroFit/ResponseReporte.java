package com.alex.helyer.mapsretrofit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseReporte {

    @SerializedName("reportes")
    @Expose
    private List<Reporte> reportes = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseReporte() {
    }

    /**
     *
     * @param reportes
     */
    public ResponseReporte(List<Reporte> reportes) {
        super();
        this.reportes = reportes;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

}