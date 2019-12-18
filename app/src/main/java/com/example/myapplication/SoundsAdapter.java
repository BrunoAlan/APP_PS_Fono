package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.room_database.Sound;

import java.util.ArrayList;
import java.util.List;

public class SoundsAdapter extends RecyclerView.Adapter<SoundsAdapter.SoundHolder>{
    private List<Sound> sound = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.practica_item,parent,false);
        return new SoundHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
        Sound currentSound = sound.get(position);
        holder.textViewNombre.setText(currentSound.getNombre_sonido());
    }

    @Override
    public int getItemCount() {
        return sound.size();
    }

    public void setSound(List<Sound>sound){
        this.sound = sound;
        notifyDataSetChanged();
    }


    class SoundHolder extends RecyclerView.ViewHolder{
        private TextView textViewNombre;

        public SoundHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombreSonido);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null  && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(sound.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Sound sound);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
