package com.example.alejandro.carritotenis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView milista;

    ArrayList<Articulo> articulos;

    ArrayList<Articulo> lista_compras;

    Button cart;

    TextView cantidadArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tienda de Tenis");

        cantidadArticulos = findViewById(R.id.tvCantArticulos);

        cart = findViewById(R.id.btnCart);

        lista_compras = new ArrayList<>();

        articulos = new ArrayList<>();

        articulos.add(new Articulo("NIKE AIR VAPORMAX FLYKNIT 2",
                "Running",
                "Con la última innovación Max Air en la planta del pie, el calzado de running para hombre Nike Air VaporMax Flyknit 2 presenta nuevos elementos de diseño. El soporte adicional alrededor del talón se combina con la sensación envolvente del Flyknit. La suela futurista completa el diseño para conseguir un calzado perfecto tanto para carreras rápidas como para conseguir un look mejorado.",
                3799,
                R.drawable.navf201,
                R.drawable.navf202));

        articulos.add(new Articulo("NIKE AIR MAX 97",
                "Lifestyle",
                "El calzado para hombre Nike Air Max 97 sigue pisando fuerte con los mismos detalles de diseño que lo hicieron famoso: líneas ondulantes, detalles reflectantes y amortiguación Max Air de longitud completa.",
                3299,
                R.drawable.nam9701,
                R.drawable.nam9702));

        articulos.add(new Articulo("NIKE AIR MAX 97 ULTRA '17",
                "Lifestyle",
                "El Nike Air Max 97 estremeció el mundo del running con su revolucionaria unidad Nike Air de largo completo. El calzado para mujer Nike Air Max 97 Ultra '17 renueva el diseño original con una confección de malla y tejido de punto para brindar una sensación más ligera y un aspecto más estilizado.",
                3299,
                R.drawable.nam97u01,
                R.drawable.nam97u02));

        articulos.add(new Articulo("NIKE AIR VAPORMAX",
                "Running",
                "El calzado de running para hombre Nike Air VaporMax incorpora el sistema reinventado de amortiguación con una parte superior actualizada. Un diseño de malla se acomoda sobre la amortiguación ligera y elástica para brindar la sensación de desafiar la gravedad.",
                3299,
                R.drawable.nav01,
                R.drawable.nav02));

        articulos.add(new Articulo("NIKE MERCURIAL VAPOR XII ACEDEMY MG",
                "Fútbol",
                "El calzado de fútbol multiterreno Nike Mercurial Vapor XII Academy proporciona un toque excepcional del balón y un ajuste seguro y cómodo que promueve la aceleración y los cambios rápidos de dirección en distintos tipos de superficie.",
                1599,
                R.drawable.nmvamg01,
                R.drawable.nmvamg02));

        articulos.add(new Articulo("NIKE MERCURIAL VAPOR XII PRO AG-PRO",
                "Fútbol",
                "El calzado de fútbol para pasto artificial Nike Mercurial Vapor XII Pro AG-PRO envuelve tu pie para ofrecerte un ajuste perfecto y un toque del balón excepcional, mientras que la placa de la suela proporciona una aceleración explosiva en el campo.",
                2349,
                R.drawable.nmvpro01,
                R.drawable.nmvpro02));

        milista = findViewById(R.id.MyList);

        ArticuloAdapter articuloAdapter = new ArticuloAdapter();

        milista.setAdapter(articuloAdapter);

        milista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, TenisActivity.class);
                intent.putExtra("articulo", articulos.get(i));
                startActivityForResult(intent, 100);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("carrito", lista_compras);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==100) {
            if(resultCode==101) {
                Articulo resultArticulo = (Articulo)data.getSerializableExtra("returnArticulo");

                lista_compras.add(resultArticulo);

                cantidadArticulos.setText("("+lista_compras.size()+")");

            }
            if(resultCode==102) {
                ArrayList<Articulo> resultCarrito = (ArrayList<Articulo>) data.getSerializableExtra("returnCarrito");

                lista_compras.clear();

                for (Articulo articulo:resultCarrito) {
                    lista_compras.add(articulo);
                }

                cantidadArticulos.setText("("+lista_compras.size()+")");
            }
        }
    }

    public class ArticuloAdapter extends ArrayAdapter<Articulo> {

        ArticuloAdapter() {
            super(MainActivity.this, R.layout.list, articulos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list, parent, false);

            TextView titulo = view.findViewById(R.id.tvTitulo);
            TextView subtitulo = view.findViewById(R.id.tvSubtitulo);
            TextView precio = view.findViewById(R.id.tvDescripcion);
            ImageView imagen = view.findViewById(R.id.ivImagen);

            Articulo miarticulo = articulos.get(position);

            titulo.setText(miarticulo.getTitulo());
            subtitulo.setText(miarticulo.getSubtitulo());
            precio.setText("$ "+miarticulo.getPrecio());

            imagen.setImageResource(miarticulo.getImagen1());

            return view;
        }
    }
}
