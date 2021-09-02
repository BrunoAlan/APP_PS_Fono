package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.room_database.palabras.Sound;


public class SoundsAdapter extends ListAdapter<Sound, SoundsAdapter.SoundHolder> {

    private static final DiffUtil.ItemCallback<Sound> DIFF_CALLBACK = new DiffUtil.ItemCallback<Sound>() {
        @Override
        public boolean areItemsTheSame(@NonNull Sound oldItem, @NonNull Sound newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Sound oldItem, @NonNull Sound newItem) {
            return oldItem.getNombre_sonido().equals(newItem.getNombre_sonido()) &&
                    oldItem.getCategoria_sonido().equals(newItem.getCategoria_sonido()) &&
                    oldItem.getPersonalizado().equals(newItem.getPersonalizado()) &&
                    oldItem.getRuta_sonido().equals(newItem.getRuta_sonido());
        }
    };
    private OnItemClickListener listener;

    public SoundsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.practica_item, parent, false);
        return new SoundHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
        Sound currentSound = getItem(position);
        holder.textViewNombre.setText(currentSound.getNombre_sonido());
    }


    public Sound getSoundAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Sound sound);
    }

    class SoundHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombre;

        public SoundHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreSonido);
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
