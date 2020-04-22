package com.example.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class tipoRopaC extends Activity {
    ArrayList<tipoRopa> prendasA = new ArrayList<tipoRopa>();
    Bitmap [] imagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descriptionropa);
        Intent intent = getIntent();
//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("imagen");
        int pos = intent.getExtras().getInt("posicion");
        try {
            imagenes = new Bitmap[new petition(this).execute("http://192.168.1.76:3000/",String.valueOf(pos)).get().length];
            imagenes = new petition(this).execute("http://192.168.1.76:3000/",String.valueOf(pos)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageView img = findViewById(R.id.ropaGeneral);
        img.setImageBitmap(imagenes[0]);
        for(int i =1;i<imagenes.length;i++)
            prendasA.add(new tipoRopa("Traje","Ventriculo","Sin descripcion",imagenes[i]));

        ListView listaPrendas = (ListView)findViewById(R.id.listPrendas);
        listaPrendas.setAdapter(new adaptadorCelda(prendasA, R.layout.tiporopa,this) {
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
        });
        ImageButton btn =  findViewById(R.id.regreso);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
