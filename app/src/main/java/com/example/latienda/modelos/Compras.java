package com.example.latienda.modelos;

public class Compras {

    private int id_compra;
    private String fecha;
    private Productos producto;

    public Compras(){

    }
    public Compras(int id_compra, String fecha, Productos producto) {
        this.id_compra = id_compra;
        this.fecha = fecha;
        this.producto = producto;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}
