package com.example.woof_woof.health;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment implements View.OnClickListener{

    FragmentNotificationsBinding binding;
    RecyclerView recyclerView;

    NotificationsDatabase db;
    ArrayList<String> nt_id, nt_type, nt_date, nt_time, nt_description;
    NotificationsAdapter notificationsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentNotificationsBinding.inflate(inflater, container, false);
        recyclerView=binding.recyclerView;
        db = new NotificationsDatabase(getContext());
        nt_id= new ArrayList<>();
        nt_type= new ArrayList<>();
        nt_date= new ArrayList<>();
        nt_time= new ArrayList<>();
        nt_description= new ArrayList<>();
        binding.addButton.setOnClickListener(this);
        getData();
        notificationsAdapter=new NotificationsAdapter(getContext(), nt_id, nt_type, nt_date, nt_time, nt_description);
        recyclerView.setAdapter(notificationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        FragmentChangeListener fc = (FragmentChangeListener) getParentFragment();
        fc.replaceFragment(new NewNotificationFragment());
    }
    void getData (){
        Cursor cursor = db.readData();
        if (cursor.getCount()==0){}
        else {
            while (cursor.moveToNext()){
                nt_id.add(cursor.getString(0));
                nt_type.add(cursor.getString(1));
                nt_date.add(cursor.getString(2));
                nt_time.add(cursor.getString(3));
                nt_description.add(cursor.getString(4));
            }
        }
    }
}