package com.example.menu;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
public abstract class adaptadorCelda extends BaseAdapter {
    private ArrayList<?> entrys;
    private int R_layout_IdView;
    private Context contexto;

    public adaptadorCelda(ArrayList<?> entrys, int r_layout_IdView, Context contexto) {
        this.entrys = entrys;
        R_layout_IdView = r_layout_IdView;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return entrys.size();
    }

    @Override
    public Object getItem(int position) {
        return entrys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            LayoutInflater vi = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R_layout_IdView,null);
        }
        onEntrada(entrys.get(position),convertView);
        return  convertView;
    }
    public abstract void onEntrada (Object entrada, View view);
}
