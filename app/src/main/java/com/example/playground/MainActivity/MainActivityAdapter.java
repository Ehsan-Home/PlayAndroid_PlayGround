package com.example.playground.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playground.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder> {
    public final ArrayList<MainActivityDataInstance> dataInstance;
    private final Context context;

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item,parent,false);
        MainActivityViewHolder mainActivityViewHolder = new MainActivityViewHolder(view);
        return mainActivityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityAdapter.MainActivityViewHolder holder, int position) {
        MainActivityDataInstance currentItem = dataInstance.get(position);
        holder.button.setText(currentItem.getTitle());
        holder.button.setCompoundDrawablesRelativeWithIntrinsicBounds(currentItem.getImageResource(),0,0,0);
        holder.button.setOnClickListener(v -> {
            Intent intent = currentItem.getDestinationIntent();
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataInstance.size();
    }


    public class MainActivityViewHolder extends RecyclerView.ViewHolder {
        public MaterialButton button;

        public MainActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.activity_main_item_button);
        }
    }

    public MainActivityAdapter(ArrayList<MainActivityDataInstance> dataInstance,
                               Context distanceIntent) {
        this.dataInstance = dataInstance;
        this.context = distanceIntent;
    }

}
