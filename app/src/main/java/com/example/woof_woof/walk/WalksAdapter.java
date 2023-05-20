package com.example.woof_woof.walk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof_woof.R;

import java.util.ArrayList;

public class WalksAdapter extends RecyclerView.Adapter<WalksAdapter.WkViewHolder> {
    private Context context;
    private ArrayList wk_id, wk_time, wk_duration;

    WalksAdapter(Context context, ArrayList wk_id, ArrayList wk_time, ArrayList wk_duration){
        this.context=context;
        this.wk_id=wk_id;
        this.wk_time=wk_time;
        this.wk_duration=wk_duration;
    }

    @NonNull
    @Override
    public WkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.walk, parent, false);
        return new WalksAdapter.WkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalksAdapter.WkViewHolder holder, int position) {
        holder.wk_time_txt.setText(String.valueOf(wk_time.get(position)));
        holder.wk_duration_txt.setText("Продолжительность: " + wk_duration.get(position) + " м.");
    }

    @Override
    public int getItemCount() {
        return wk_id.size();
    }

    public class WkViewHolder extends RecyclerView.ViewHolder{

        TextView wk_time_txt, wk_duration_txt;

        public WkViewHolder(@NonNull View itemView) {
            super(itemView);
            wk_time_txt=itemView.findViewById(R.id.time_txt);
            wk_duration_txt=itemView.findViewById(R.id.duration_txt);
        }
    }
}
