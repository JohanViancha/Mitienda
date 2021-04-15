package com.example.latienda.modelos;

import android.graphics.Bitmap;

public class Productos {

    private int idproducto;
    private String nombre;
    private String descripcion;
    private double valor;
    private Bitmap imagen;


    public Productos() {
    }
    public Productos(String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Productos(int idproducto, String descripcion, double valor, Bitmap imagen) {
        this.descripcion = descripcion;
        this.valor = valor;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdproducto() {return idproducto; }

    public void setIdproducto(int idproducto) {this.idproducto = idproducto;}

    public String getNombre() {return nombre; }

    public void setNombre(String nombre) {this.nombre = nombre;}
    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
