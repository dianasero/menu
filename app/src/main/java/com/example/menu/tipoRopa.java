package com.example.menu;

import android.graphics.Bitmap;

public class tipoRopa {
    String prenda;
    String precio;
    String descripcion;
//    boolean tintoreria;
//    boolean lavanderia;
//    boolean planchado;
//    boolean costura;
//    boolean tinEcologica;
    Bitmap img;

    public tipoRopa(String prenda, String precio, String descripcion, Bitmap img) {
        this.prenda = prenda;
        this.precio = precio;
        this.descripcion = descripcion;
//        this.tintoreria = tintoreria;
//        this.lavanderia = lavanderia;
//        this.planchado = planchado;
//        this.costura = costura;
//        this.tinEcologica = tinEcologica;
        this.img = img;
    }

//    public boolean isTintoreria() {
//        return tintoreria;
//    }
//
//    public void setTintoreria(boolean tintoreria) {
//        this.tintoreria = tintoreria;
//    }
//
//    public boolean isLavanderia() {
//        return lavanderia;
//    }
//
//    public void setLavanderia(boolean lavanderia) {
//        this.lavanderia = lavanderia;
//    }
//
//    public boolean isPlanchado() {
//        return planchado;
//    }
//
//    public void setPlanchado(boolean planchado) {
//        this.planchado = planchado;
//    }
//
//    public boolean isCostura() {
//        return costura;
//    }
//
//    public void setCostura(boolean costura) {
//        this.costura = costura;
//    }
//
//    public boolean isTinEcologica() {
//        return tinEcologica;
//    }
//
//    public void setTinEcologica(boolean tinEcologica) {
//        this.tinEcologica = tinEcologica;
//    }

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
