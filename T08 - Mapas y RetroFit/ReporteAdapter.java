package com.alex.helyer.mapsretrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private List<Reporte> reportes;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre,distancia,comentario;
        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.reporteNombre);
            distancia = view.findViewById(R.id.reporteDistancia);
            comentario = view.findViewById(R.id.reporteDescripcion);
        }
    }

    public ReporteAdapter(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_reporte, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reporte rep = reportes.get(position);
        holder.nombre.setText(rep.getHashtag());
        holder.distancia.setText( rep.getDistancia().intValue() + "m");
        holder.comentario.setText(rep.getComentario());
    }

    @Override
    public int getItemCount() {
        return reportes.size();
    }
}
