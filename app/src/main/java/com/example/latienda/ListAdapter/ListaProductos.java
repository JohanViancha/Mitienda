package com.example.latienda.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latienda.R;
import com.example.latienda.modelos.Productos;

import java.util.List;

public class ListaProductos extends RecyclerView.Adapter<ListaProductos.ViewHolder>
        implements View.OnClickListener {

    private List<Productos> productos;
    private LayoutInflater inflater;
    private Context context;
    private View.OnClickListener listener;

    public ListaProductos(List<Productos> productos, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaProductos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_productos,null);
        view.setOnClickListener(this);
        return new ListaProductos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaProductos.ViewHolder holder, int position) {
        holder.pintarDatos(this.productos.get(position));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }
    @Override
    public void onClick(View v) {

        if(listener !=  null){
            listener.onClick(v);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView descripcion, valor;
        private ImageView imagen;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            descripcion = itemView.findViewById(R.id.txtdescripcion);
            valor = itemView.findViewById(R.id.txtvalor);
            imagen = itemView.findViewById(R.id.ivimagen_pro);
        }

        public void pintarDatos(final Productos list_productos){
            descripcion.setText(list_productos.getDescripcion());
            valor.setText(String.valueOf(list_productos.getValor()));
            imagen.setImageBitmap(list_productos.getImagen());
        }
    }
}
