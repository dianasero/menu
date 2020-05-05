package com.example.menu;
import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class tipoRopaC extends Activity {
    ArrayList<tipoRopa> prendasA = new ArrayList<tipoRopa>();
    Bitmap [] imagenes;
    adaptadorCelda ad = null;
    @Override
    protected void onResume() {
        super.onResume();
        Button tintoreria = (Button)findViewById(R.id.tintoreria);
        Button lavanderia = (Button)findViewById(R.id.lavanderia);
        Button planchado = (Button)findViewById(R.id.planchado);
        Button tintoreriaE = (Button)findViewById(R.id.tintoreriaE);
        Button costura = (Button)findViewById(R.id.costura);
        final Button[] buttonA = {tintoreria,lavanderia,planchado,tintoreriaE,costura};
        final TextView actual = findViewById(R.id.actual);
        tintoreria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarColor(0,buttonA);
                prendasA.clear();
                for(int i =4;i<imagenes.length;i++)
                    prendasA.add(new tipoRopa("Tintoreria","2.00","Sin descripcion",imagenes[i]));
                ad.notifyDataSetChanged();
                actual.setText("Tintoreria");
            }
        });

        lavanderia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarColor(1,buttonA);
                prendasA.clear();
                for(int i =1;i<imagenes.length-1;i++)
                    prendasA.add(new tipoRopa("Lavanderia","5.00","Sin descripcion",imagenes[i]));
                ad.notifyDataSetChanged();
                actual.setText("Lavanderia");
            }
        });
        planchado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarColor(2,buttonA);
                prendasA.clear();
                for(int i =3;i<imagenes.length-2;i++)
                    prendasA.add(new tipoRopa("Planchado","17.00","Sin descripcion",imagenes[i]));
                ad.notifyDataSetChanged();
                actual.setText("Planchado");
            }
        });
        tintoreriaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarColor(3,buttonA);
                prendasA.clear();
                for(int i =2;i<imagenes.length-3;i++)
                    prendasA.add(new tipoRopa("Tintoreria Ecologica","14.00","Sin descripcion",imagenes[i]));
                ad.notifyDataSetChanged();
                actual.setText("TintoreriaE");
            }
        });
        costura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarColor(4,buttonA);
                prendasA.clear();
                for(int i=2;i<imagenes.length;i++)
                    prendasA.add(new tipoRopa("Costura","24.00","Sin descripcion",imagenes[i]));
                ad.notifyDataSetChanged();
                actual.setText("Costura");
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descriptionropa);
        Intent intent = getIntent();
        int pos = intent.getExtras().getInt("posicion");
        final ListView listaPrendas = (ListView)findViewById(R.id.listPrendas);
        try {
            imagenes = new Bitmap[new petition(this).execute("http://192.168.1.76:8080/",String.valueOf(pos)).get().length];
            imagenes = new petition(this).execute("http://192.168.1.76:8080/",String.valueOf(pos)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageView img = findViewById(R.id.ropaGeneral);
        img.setImageBitmap(imagenes[0]);
        for(int i =1;i<imagenes.length;i++)
            prendasA.add(new tipoRopa("Traje","Ventriculo","Sin descripcion",imagenes[i]));
        ad = new adaptadorCelda(prendasA, R.layout.tiporopa,this) {
            @Override
            public void onEntrada(Object entrada, View view) {
                ImageView img = (ImageView)view.findViewById(R.id.imagenR);
                img.setImageBitmap(((tipoRopa)entrada).getImg());
                TextView prenda = (TextView)view.findViewById(R.id.tipoPrenda);
                prenda.setText(((tipoRopa)entrada).getPrenda());
                TextView descripcion = (TextView)view.findViewById(R.id.descripcion);
                descripcion.setText(((tipoRopa)entrada).getDescripcion());
                TextView precio = (TextView)view.findViewById(R.id.precio);
                precio.setText(((tipoRopa)entrada).getPrecio());

            }
        };
        listaPrendas.setAdapter(ad);
        ImageButton btn =  findViewById(R.id.regreso);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void cambiarColor(int boton, Button[] buttonA){
        buttonA[boton].setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorGray));
        for(Button b: buttonA)
            if(b.getId()!= buttonA[boton].getId())
                b.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
    }
}
