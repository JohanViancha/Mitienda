package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CrearProducto extends AppCompatActivity {

    private EditText descripcion, valor, nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);
        nombre = findViewById(R.id.txtnombre);
        descripcion = (EditText) findViewById(R.id.txtdescripcion_pro);
        valor = (EditText) findViewById(R.id.txtvalor_pro);
    }

    //Metodo para registrar un nuevo producto
    public void registrar_producto(View view){
        String nombre =this.nombre.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        double valor =(Double) Double.parseDouble(this.valor.getText().toString());

        if(!descripcion.isEmpty() && valor!=0){
            try {
                Administracion_BD admin = new Administracion_BD(this, "tienda", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                ContentValues producto = new ContentValues();

                SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
                int id_usuario = sharedpreferences.getInt("id_usuario",-1);

                producto.put("nombre_pro", nombre);
                producto.put("descripcion_pro", descripcion);
                producto.put("valor_pro", valor);
                producto.put("id_usuario", id_usuario);
                db.insert("producto", null, producto);
                db.close();
                Toast.makeText(this, "Se ha creado exitosamente el producto", Toast.LENGTH_LONG).show();
                Intent inte = new Intent(this, Menu.class);
                startActivity(inte);
            }catch (Exception ex){
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            }


        }

    }
}