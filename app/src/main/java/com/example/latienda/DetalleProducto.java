package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleProducto extends AppCompatActivity {

    private TextView descripcion, valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        descripcion = findViewById(R.id.tvdescripcion);
        valor = findViewById(R.id.tvvalor);

        descripcion.setText(getIntent().getStringExtra("descripcion"));
        valor.setText(String.valueOf(getIntent().getDoubleExtra("valor",0)));
    }
}