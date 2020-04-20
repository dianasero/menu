package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.app.*;
import android.util.Base64;
import android.webkit.*;
import android.widget.*;

import java.io.*;
import java.lang.ref.WeakReference;
import java.net.*;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
//import android.support.v4.app;

import java.util.*;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    private LinearLayout outerLinearLayout, layxml;
    private TextView text1;
    private Bitmap [] imagenes = new Bitmap[0];
    ArrayList<lavanderia> datos = new ArrayList<lavanderia>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Descarga de imagenes
        try {
            imagenes = new Bitmap[new peticion(this).execute("http://192.168.1.76:3000/").get().length];
            imagenes = new peticion(this).execute("http://192.168.1.76:3000/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Botones Listeners
        final ImageButton boton1 = (ImageButton) findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        final ImageButton boton2 = (ImageButton) findViewById(R.id.boton2);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        final ImageButton boton3 = (ImageButton) findViewById(R.id.boton3);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        final ImageButton boton4 = (ImageButton) findViewById(R.id.boton4);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        final ImageButton boton5 = (ImageButton) findViewById(R.id.boton5);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        //Añadir Datos
        ListView lista =(ListView) findViewById(R.id.listViewMain);
        for(int i =0;i<imagenes.length;i++)
            datos.add(new lavanderia(imagenes[i],"Prenda estandar",String.valueOf(Math.random()*12+1)+":00",String.valueOf(Math.random()*20+10),String.valueOf(Math.random()*12)));

        lista.setAdapter(new adaptadorCelda(datos, R.layout.lavanda,this) {
            @Override
            public void onEntrada(Object entrada, View view){
                ImageView img = (ImageView)view.findViewById(R.id.imageL);
//                img.setImageBitmap(Bitmap.createScaledBitmap(((lavanderia)entrada).getImagen(), img.getWidth(), img.getHeight(), false));
                img.setImageBitmap(((lavanderia)entrada).getImagen());
                TextView tipo = (TextView)view.findViewById(R.id.tipoPrenda);
                tipo.setText(((lavanderia)entrada).getTipo());
                TextView hora = (TextView)view.findViewById(R.id.horario);
                hora.setText(((lavanderia)entrada).getHora());
                TextView precio = (TextView)view.findViewById(R.id.costo);
                precio.setText(((lavanderia)entrada).getPrecio());
                TextView descuento = (TextView)view.findViewById(R.id.descuento);
                if(!((lavanderia)entrada).getDescuento().equals("0"))
                    descuento.setText(((lavanderia)entrada).getDescuento());
                else
                    descuento.setVisibility(View.GONE);
            }
        });


    }

}
class peticion extends AsyncTask<String, Void, Bitmap[]> {
    private WeakReference<Activity> mActivity;
    private byte [][] imagenes;
    private int numImagenes;
    private Bitmap [] imgs;
    peticion(Activity activity) {
        super();
        mActivity = new WeakReference<Activity>( activity );//Del principal
    }

    @Override
    public Bitmap[] doInBackground(String... exten) {
        URL url;
        HttpURLConnection urlConnection = null;
        StringBuilder sb = new StringBuilder();
        int i=0;
        ByteArrayOutputStream baos =  new ByteArrayOutputStream();//imagen1
        try {
            url = new URL(exten[0]+"imagen"+i);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            numImagenes = Integer.parseInt(sb.toString());
            i++;
            imagenes= new byte[numImagenes][];
            //Leer las imagenes
            for(int b=0; b<numImagenes;b++){
                baos =  new ByteArrayOutputStream();
                url = new URL(exten[0]+"imagen"+i);
                urlConnection = (HttpURLConnection) url.openConnection();
                in = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                int data = reader.read();
                while (data != -1) {
                    byte current = (byte) data;
                    baos.write(current);
                    data = reader.read();
                }
                imagenes[b]= new byte[baos.toByteArray().length];
                imagenes[b] = baos.toByteArray(); Log.d("Descarga"+i,"listo");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Exception on request",e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.d("Respuesta","Descarga Completada");
        }
        try{
            imgs = new Bitmap[numImagenes];
            byte[] bytes;Bitmap bitmap;
            for(int h=0;h<numImagenes;h++){
                bytes = Base64.decode(imagenes[h], Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgs[h] = bitmap;
            }
            Log.d("Realizado","Listo"+imgs.length);
        }
        catch (Exception e){
            Log.d("exception", e.getMessage());
        }
        return imgs;
    }

    public void onProgressUpdate(Integer... progress) {

    }
    @Override
    public void onPostExecute(Bitmap[] res) {
        super.onPostExecute(res);

    }
}
