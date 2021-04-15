package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalleProducto extends AppCompatActivity {

    private TextView descripcion, valor, nombre;
    private ImageView imagen;
    private int id_producto = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        nombre = findViewById(R.id.tvnombre);
        descripcion = findViewById(R.id.tvdescripcion);
        valor = findViewById(R.id.tvvalor);
        imagen = findViewById(R.id.imvdetalle);

        id_producto = getIntent().getIntExtra("id_producto",0);
        nombre.setText(getIntent().getStringExtra("nombre"));
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        valor.setText(String.valueOf(getIntent().getDoubleExtra("valor",0)));
        byte[] byteArray = getIntent().getByteArrayExtra("imagen");
        Bitmap img = (Bitmap) BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
        imagen.setImageBitmap(img);
    }

    public void hacerCompra(View view) {

        Administracion_BD admin = new Administracion_BD(this, "tienda", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();


        try{
            ContentValues content = new ContentValues();
            long ahora = System.currentTimeMillis();
            Date fecha = new Date(ahora);
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            String salida = df.format(fecha);

            content.put("fecha_com", salida);
            content.put("id_producto", id_producto);


            db.insert("compra", null, content);
            Toast.makeText(this, "La compra ha sido registrada", Toast.LENGTH_LONG).show();

        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();

        }



    }
}