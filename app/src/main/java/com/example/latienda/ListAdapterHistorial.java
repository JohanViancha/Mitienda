package com.example.latienda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.latienda.Compras;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapterHistorial extends RecyclerView.Adapter<ListAdapterHistorial.ViewHolder> {

    private List<Compras> compras;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterHistorial(List<Compras> compras, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.compras = compras;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapterHistorial.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_compras, null);
        return new ListAdapterHistorial.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterHistorial.ViewHolder holder, int position) {
        holder.pintarDatos(this.compras.get(position));
    }

    @Override
    public int getItemCount() {
        return compras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView descripcion, valor,fecha;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.tvfecha_com);
           descripcion = itemView.findViewById(R.id.tvdescripcion_com);
            valor = itemView.findViewById(R.id.tvvalor_com);
        }

        public void pintarDatos(final Compras list_compras){
            fecha.setText(list_compras.getFecha());
            descripcion.setText(list_compras.getProducto().getDescripcion());
            valor.setText("$ " + String.valueOf(list_compras.getProducto().getValor()));
        }
    }
}
