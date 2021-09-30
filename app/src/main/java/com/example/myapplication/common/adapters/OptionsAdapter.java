package com.example.myapplication.common.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.common.entities.OptionAnswer;
import com.example.myapplication.databinding.ItemOptionBinding;
import com.google.android.material.button.MaterialButton;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder> {
    private List<String> mOptions;
    private Context mContext;
    private OnOptionClickListener mListener;

    public OptionsAdapter(List<String> mOptions, OnOptionClickListener mListener) {
        this.mOptions = mOptions;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String option = mOptions.get(position);
        holder.binding.btnOption.setText(option);
        holder.setOnClickListener(holder.binding.btnOption, position, mListener);
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemOptionBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemOptionBinding.bind(itemView);
        }

        void setOnClickListener(MaterialButton btnOption, int index, OnOptionClickListener listener) {
            btnOption.setOnClickListener(button -> listener.onClickListener(btnOption, index));
        }
    }
}
