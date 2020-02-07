package com.example.myapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.room_database.resultados.Resultado;

public class ResultadosAdapter extends ListAdapter<Resultado, ResultadosAdapter.ResultadosHolder> {
    private ResultadosAdapter.OnItemClickListener listener;

    public ResultadosAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Resultado> DIFF_CALLBACK = new DiffUtil.ItemCallback<Resultado>() {
        @Override
        public boolean areItemsTheSame(@NonNull Resultado oldItem, @NonNull Resultado newItem) {
            return oldItem.getId() == newItem.getId();        }

        @Override
        public boolean areContentsTheSame(@NonNull Resultado oldItem, @NonNull Resultado newItem) {
            return oldItem.getFecha().equals(newItem.getFecha()) &&
                    oldItem.getTipo_ejercicio().equals(newItem.getTipo_ejercicio()) &&
                    oldItem.getCategoria().equals(newItem.getCategoria()) &&
                    oldItem.getRuido().equals(newItem.getRuido()) &&
                    oldItem.getErrores().equals(newItem.getErrores());
        }
    };


    @NonNull
    @Override
    public ResultadosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resultado_item, parent, false);
        return new ResultadosHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadosHolder holder, int position) {
        Resultado currentResultado = getResultadoAt(position);
        holder.textViewFecha.setText(currentResultado.getFecha());
        holder.textViewCategoria.setText(currentResultado.getCategoria());
        holder.textViewEjercicio.setText(currentResultado.getTipo_ejercicio());
        holder.textViewRuido.setText(currentResultado.getRuido());
        holder.textViewResultados.setText(currentResultado.getResultado()+"/10");
    }

    public Resultado getResultadoAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Resultado resultado);
    }

    class ResultadosHolder extends RecyclerView.ViewHolder {
        private TextView textViewFecha;
        private TextView textViewEjercicio;
        private TextView textViewCategoria;
        private TextView textViewRuido;
        private TextView textViewResultados;

        public ResultadosHolder(@NonNull View itemView) {
            super(itemView);
            textViewFecha = itemView.findViewById(R.id.tvFecha);
            textViewEjercicio = itemView.findViewById(R.id.tvEjercicio);
            textViewCategoria = itemView.findViewById(R.id.tvCategoria);
            textViewRuido = itemView.findViewById(R.id.tvRuido);
            textViewResultados = itemView.findViewById(R.id.tvResultado);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

}


