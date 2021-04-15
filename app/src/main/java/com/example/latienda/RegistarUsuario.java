package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistarUsuario extends AppCompatActivity {

    private EditText nombre, apellidos, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_usuario);

        nombre = (EditText) findViewById(R.id.txtnombres_reg);
        apellidos = (EditText) findViewById(R.id.txtcontraseña_reg);
        email = (EditText) findViewById(R.id.txtemail_reg);
        password = (EditText) findViewById(R.id.txtcontraseña_reg);


    }

    //Metodo para registrar un nuevo usuario
    public void registra_usuario(View view){


        String nombres = this.nombre.getText().toString();
        String apellidos = this.apellidos.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();


        if(!nombres.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            try {

                Administracion_BD admin =  new Administracion_BD(this, "tienda", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();

                ContentValues usuario = new ContentValues();

                usuario.put("nombre_usu", nombres);
                usuario.put("apellido_usu", apellidos);
                usuario.put("email_usu", email);
                usuario.put("password_usu", password);

                //Inserción del usuario en BD
                db.insert("usuario", null, usuario);
                db.close();
                Toast.makeText(this, "El usuario ha sido registrado", Toast.LENGTH_LONG).show();



                Intent inte = new Intent(this, MainActivity.class);
                startActivity(inte);
                limpiarFormulario();
            }catch (Exception ex){

                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();

            }


        }
        else{
            Toast.makeText(this, "Por favor ingrese todos los datos del usuario", Toast.LENGTH_SHORT).show();
        }


    }

    public void limpiarFormulario(){
        this.nombre.setText("");
        this.apellidos.setText("");
        this.email.setText("");
        this.password.setText("");
    }
}