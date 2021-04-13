package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    private int id_usuario=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        id_usuario = getIntent().getIntExtra("usuario",0);

    }

    public void cerrar_sesion(View view){
        id_usuario=0;
        Intent inte = new Intent(this, MainActivity.class);
        startActivity(inte);
    }
}