package com.example.latienda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearProducto extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText descripcion, valor, nombre;
    private ImageView imageview;
    private Bitmap imageBitmap;
    private byte[] imagebit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);
        nombre = findViewById(R.id.txtnombre);
        descripcion = (EditText) findViewById(R.id.txtdescripcion_pro);
        valor = (EditText) findViewById(R.id.txtvalor_pro);
        imageview = (ImageView) findViewById(R.id.ivimage_pro);

        if (ContextCompat.checkSelfPermission(CrearProducto.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CrearProducto.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CrearProducto.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

    }

    //Metodo para registrar un nuevo producto
    public void registrar_producto(View view){
        String nombre =this.nombre.getText().toString();
        String descripcion = this.descripcion.getText().toString();
        double valor = 0;
        if(!(this.valor.getText().toString()).isEmpty()){
            valor =(Double) Double.parseDouble(this.valor.getText().toString());
        }

        if(!descripcion.isEmpty() && valor!=0 && imagebit != null){

            System.out.println(descripcion);
            System.out.println(valor);
            System.out.println(imagebit);

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
                producto.put("imagen_pro",imagebit);
                db.insert("producto", null, producto);
                db.close();
                Toast.makeText(this, "Se ha creado exitosamente el producto", Toast.LENGTH_LONG).show();
                Intent inte = new Intent(this, Menu.class);
                startActivity(inte);
            }catch (Exception ex){
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            }


        }else{
            Toast.makeText(this, "Por favor ingrese todos los datos del producto", Toast.LENGTH_LONG).show();

        }

    }

    public void selecciinarImagen(View view){

        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        takePictureIntent.setType("image/");
        startActivityForResult(takePictureIntent.createChooser(takePictureIntent, "Seleccione la aplicaci??n"), 2);

    }



    public String createImageFile(){
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        return imageFileName+".jpg";
    }



    public  void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
           Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageview.setImageBitmap(imageBitmap);
           try {
               ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
               imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , baos);
               imagebit = baos.toByteArray();
               baos.close();
            }catch (Exception ex){
               ex.printStackTrace();
            }
        }

    }
}