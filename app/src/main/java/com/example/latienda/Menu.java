package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private int id_usuario=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void ir_CrerProducto(View view){

        Intent inte = new Intent(this, CrearProducto.class);
        inte.putExtra("usuario", id_usuario);
        startActivity(inte);
    }

    public void cerrar_sesion(View view){
        id_usuario=0;
        Intent inte = new Intent(this, MainActivity.class);
        startActivity(inte);
    }

    public void ir_VisualizarProducto(View view){
        Intent inte = new Intent(this, VisualizarProductos.class);
        inte.putExtra("usuario", id_usuario);
        startActivity(inte);
    }
}