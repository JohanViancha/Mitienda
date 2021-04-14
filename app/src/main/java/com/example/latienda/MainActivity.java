package com.example.latienda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        usuario = (EditText) findViewById(R.id.txtusuario);
        password = (EditText) findViewById(R.id.txtpassword);
    }


    public void iniciar_sesion(View view){


        String usuario = this.usuario.getText().toString();
        String password = this.password.getText().toString();

        try {
            Administracion_BD admin = new Administracion_BD(this,"tienda", null, 1);
            SQLiteDatabase db =  admin.getWritableDatabase();


            if(!usuario.isEmpty() && !password.isEmpty()){
                Cursor fila = db.rawQuery("select * from usuario where email_usu=\'"+usuario+"\' and password_usu=\'"+password+"\'",null);
                if(fila.moveToFirst()){


                    SharedPreferences sharedpreferences = getSharedPreferences("sesion_usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt("id_usuario" , fila.getInt(0));
                    editor.commit();

                    Intent inte = new Intent(this, Menu.class);
                    startActivity(inte);

                }
                else{
                    Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Por favor ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    public void irRegistrar(View view){

        Intent inten = new Intent(this, RegistarUsuario.class);
        startActivity(inten);
    }


}