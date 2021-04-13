package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VisualizarProductos extends AppCompatActivity {
    private int id_usuario =0;
    List<Productos> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_productos);

        productos = new ArrayList<>();

        productos.add(new Productos("Producto 1", 4000));
        productos.add(new Productos("Producto 2", 4000));
        productos.add(new Productos("Producto 3", 2000));

        ListaProductos adapter = new ListaProductos(productos,this);

        RecyclerView recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);


        id_usuario = getIntent().getIntExtra("usuario",0);

        listar_productos();
    }



    public void listar_productos(){

        try{

            Administracion_BD admin = new Administracion_BD(this, "tienda", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            System.out.println("Id usuario "+ id_usuario);
            Cursor fila = db.rawQuery("select * from producto where id_usuario=\'"+id_usuario+"\'",null);
            if(fila.moveToFirst()){
                Toast.makeText(this, "Si existe productos", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No existe productos", Toast.LENGTH_SHORT).show();

            }
            db.close();

        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}