package com.example.latienda;

public class Productos {

    private int idproducto;
    private String nombre;
    private String descripcion;
    private double valor;


    public Productos() {
    }
    public Productos(String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Productos(int idproducto, String descripcion, double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
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
}
