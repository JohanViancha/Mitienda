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
        db.execSQL("create table producto (" +
                "id_producto integer primary key, " +
                "nombre_pro text, " +
                "descripcion_pro text, " +
                "valor_pro money, " +
                "id_usuario integer, " +
                "foreign key (id_usuario) references usuario (id_usuario))");

        db.execSQL("create table usuario (" +
                "id_usuario integer primary key," +
                " nombre_usu text, apellido_usu text," +
                " email_usu text, password_usu text)");

        db.execSQL("create table compra (" +
                "id_compra integer primary key," +
                "fecha_com date , " +
                "id_producto integer," +
                " foreign key (id_producto) references producto (id_producto))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
