package com.example.ngetichconverter.SharedPreferences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ngetichconverter.R;

import java.util.ArrayList;

public class SharedPreferenceAdapter extends RecyclerView.Adapter<SharedPreferenceAdapter.ViewHolder> {
    private ArrayList<PreferenceItems> recentsearch;
    Context context;

    @NonNull
    @Override
    public SharedPreferenceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SharedPreferenceAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_search,parent,false)) ;

    }

    @Override
    public void onBindViewHolder(@NonNull SharedPreferenceAdapter.ViewHolder holder, int position) {
        PreferenceItems prefs=recentsearch.get(position);

        holder.country1.setText(prefs.getCountry1());
        holder.country2.setText(prefs.getCountry2());
    }

    @Override
    public int getItemCount() {
        return recentsearch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView country1 ,country2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            country1=itemView.findViewById(R.id.text1);
            country2=itemView.findViewById(R.id.text2);
        }
    }
}
