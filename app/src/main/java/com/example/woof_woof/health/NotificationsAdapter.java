package com.example.woof_woof.health;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.MainActivity;
import com.example.woof_woof.R;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NtViewHolder> {

    private Context context;
    private ArrayList nt_id, nt_type, nt_date, nt_time, nt_description;
    AdapterView.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    NotificationsAdapter(Context context,
                         ArrayList nt_id,
                         ArrayList nt_type,
                         ArrayList nt_date,
                         ArrayList nt_time,
                         ArrayList nt_description){
        this.context=context;
        this.nt_id=nt_id;
        this.nt_type=nt_type;
        this.nt_date=nt_date;
        this.nt_time=nt_time;
        this.nt_description=nt_description;
    }

    @NonNull
    @Override
    public NtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notification, parent, false);
        return new NtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.NtViewHolder holder, int position) {
        holder.nt_type_txt.setText(String.valueOf(nt_type.get(position)));
        holder.nt_when_txt.setText(nt_date.get(position)+", "+nt_time.get(position));
        holder.nt_description_txt.setText(String.valueOf(nt_description.get(position)));
    }

    @Override
    public int getItemCount() {
        return nt_id.size();
    }

    public class NtViewHolder extends RecyclerView.ViewHolder{

         TextView nt_type_txt, nt_when_txt, nt_description_txt;
         LinearLayout mainLayout;

        public NtViewHolder(@NonNull View itemView) {
            super(itemView);
            nt_type_txt=itemView.findViewById(R.id.type_txt);
            nt_when_txt=itemView.findViewById(R.id.when_txt);
            nt_description_txt=itemView.findViewById(R.id.description_txt);

        }
    }

}
