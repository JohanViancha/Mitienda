package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.latienda.ListAdapter.ListaProductos;
import com.example.latienda.modelos.Productos;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class VisualizarProductos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_productos);

        listar_productos();
    }


    public void listar_productos(){

        List<Productos> Listproducto = new ArrayList<Productos>();
        try{

            Administracion_BD admin = new Administracion_BD(this, "tienda", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
            int id_usuario = sharedpreferences.getInt("id_usuario",-1);


            Cursor fila = db.rawQuery("select * from producto where id_usuario=\'"+id_usuario+"\'",null);

            if(fila.getCount() != 0){
                while (fila.moveToNext()){
                    Productos prod = new Productos();
                    prod.setIdproducto(fila.getInt(0));
                    prod.setNombre(fila.getString(1));
                    prod.setDescripcion(fila.getString(2));
                    prod.setValor(fila.getDouble(3));
                    byte[] byteArray = fila.getBlob(5);
                    Bitmap img = (Bitmap) BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
                    prod.setImagen(img);

                    Listproducto.add(prod);
                }

                ListaProductos adapter = new ListaProductos(Listproducto,this);

                RecyclerView recycler = findViewById(R.id.recyclerView);
                recycler.setHasFixedSize(true);
                recycler.setLayoutManager(new LinearLayoutManager(this));
                recycler.setAdapter(adapter);

                Intent inte = new Intent(this, DetalleProducto.class);
                adapter.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        inte.putExtra("id_producto",Listproducto.get(recycler.getChildAdapterPosition(v)).getIdproducto());
                        inte.putExtra("nombre",Listproducto.get(recycler.getChildAdapterPosition(v)).getNombre());
                        inte.putExtra("descripcion",Listproducto.get(recycler.getChildAdapterPosition(v)).getDescripcion());
                        inte.putExtra("valor",Listproducto.get(recycler.getChildAdapterPosition(v)).getValor());

                        try {
                            byte[] imagebit;
                            Bitmap imageBitmap = Listproducto.get(recycler.getChildAdapterPosition(v)).getImagen();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
                            imagebit = baos.toByteArray();
                            baos.close();
                            inte.putExtra("imagen",imagebit);
                        }catch (Exception ex){
                            System.out.println(ex.getMessage());
                        }

                        startActivity(inte);
                    }
                });


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