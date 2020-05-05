package com.example.menu;

import android.graphics.Bitmap;

public class tipoRopa {
    String prenda;
    String precio;
    String descripcion;
    Bitmap img;

    public tipoRopa(String prenda, String precio, String descripcion, Bitmap img) {
        this.prenda = prenda;
        this.precio = precio;
        this.descripcion = descripcion;
        this.img = img;
    }

    public String getPrenda() {
        return prenda;
    }

    public void setPrenda(String prenda) {
        this.prenda = prenda;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
