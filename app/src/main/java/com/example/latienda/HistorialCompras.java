package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistorialCompras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_compras);

        listar_compras();
    }

    public void listar_compras(){

        List<Compras> Listcompras = new ArrayList<Compras>();
        try{

            Administracion_BD admin = new Administracion_BD(this, "tienda", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
            int id_usuario = sharedpreferences.getInt("id_usuario",-1);


            Cursor fila = db.rawQuery("select * from compra c inner join producto p on p.id_producto = c.id_producto" +
                    " where p.id_usuario=\'"+id_usuario+"\'",null);

            if(fila.getCount() != 0){
                while (fila.moveToNext()){
                    Compras comp = new Compras();
                    comp.setFecha(fila.getString(1));
                  //  comp.setProducto(new Productos(fila.getString(5), fila.getDouble(6)));


                    Listcompras.add(comp);
                }

                ListAdapterHistorial adapter = new ListAdapterHistorial(Listcompras,this);

                RecyclerView recycler = findViewById(R.id.recycler_historial);
                recycler.setHasFixedSize(true);
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);
                Toast.makeText(this, "Si existe compras", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(this, "No existe compras", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }catch (Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}