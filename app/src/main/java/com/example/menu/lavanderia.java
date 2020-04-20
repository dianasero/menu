package com.example.menu;

import android.graphics.Bitmap;

public class lavanderia {
    private Bitmap imagen;
    private String tipo;
    private String hora;
    private String precio;
    private String descuento;

    public lavanderia(Bitmap imagen, String tipo, String hora, String precio, String descuento) {
        this.imagen = imagen;
        this.tipo = tipo;
        this.hora = hora;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }
}
