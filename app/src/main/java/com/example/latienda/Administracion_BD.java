package com.example.latienda;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import androidx.annotation.Nullable;

public class Administracion_BD extends SQLiteOpenHelper {

    public Administracion_BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table producto (id_producto int primary key, descripcion_pro varchar(100), valor_pro money, id_usuario int, foreign key (id_usuario) references usuario (id_usuario) )");
        db.execSQL("create table usuario (id_usuario int primary key, nombre_usu varchar(100), apellido_usu varchar(100), email_usu varchar(100), password_usu varchar(255))");
        db.execSQL("create table compra (id_compra int primary key, fecha_com date , id_producto int, foreign key (id_producto) references producto (id_producto))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
