package com.example.woof_woof.walk;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.Gravity;;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentWalkCalendarBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;

import java.util.ArrayList;
import java.util.Calendar;

public class WalkCalendarFragment extends Fragment {

    FragmentWalkCalendarBinding binding;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    WalksAdapter walksAdapter;
    FragmentChangeListener fc;
    String date, formattedDate;
    ArrayList<String> wk_id, wk_time,  wk_duration;
    WalksDatabase db;
    int recommendedTime;
    int actualTime=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWalkCalendarBinding.inflate(inflater, container, false);
        fc = (FragmentChangeListener) getParentFragment();
        recyclerView=binding.recyclerView;
        db= new WalksDatabase(getContext());
        wk_id=new ArrayList<>();
        wk_time=new ArrayList<>();
        wk_duration=new ArrayList<>();
        setDate();
        getData();
        walksAdapter = new WalksAdapter(getContext(), wk_id, wk_time, wk_duration);
        recyclerView.setAdapter(walksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setRecommendation();
        setProgressAndAnimate();
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
    }
    void getData(){
        Cursor cursor = db.readData();
        if (cursor.getCount()==0){ } else{
            while (cursor.moveToNext()){
                if (cursor.getString(1).equals(date)){
                    wk_id.add(cursor.getString(0));
                    wk_time.add(cursor.getString(2)+" - "+cursor.getString(3));
                    String duration = cursor.getString(4);
                    wk_duration.add(duration);
                    actualTime+=Integer.parseInt(duration);
                }
            }
        }
        if (wk_id.size()==0){
            binding.emptyImage.setVisibility(View.VISIBLE);
            binding.emptyText.setVisibility(View.VISIBLE);
        }
    }

    void setRecommendation(){
        sharedPreferences=getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String years = sharedPreferences.getString("YEARS", "3");
        String months = sharedPreferences.getString("MONTHS", "10");
        int age = Integer.parseInt(years)*12+Integer.parseInt(months);
        if (age<=5) recommendedTime=30;
        else if (age >= 36) recommendedTime=150;
        else{
            double result = Math.pow(age, 0.33)*30;
            recommendedTime = (int) (Math.round(result / 10) * 10);
        }
        binding.recommendation.setText(recommendedTime + " мин/день");
    }

    void setProgressAndAnimate(){
        binding.progressBar.setMax(recommendedTime);
        ObjectAnimator animation = ObjectAnimator.ofInt(binding.progressBar, "progress", actualTime);
        animation.setDuration(1000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        binding.progressText.setText(actualTime + " мин. / "+recommendedTime+" мин.");
    }

    void setDate(){
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        date = dayOfMonth + ":" + month;

        String[] months = new String[]{"января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        String monthName = months[month];
        formattedDate=dayOfMonth + " " + monthName;

        binding.date.setText("Сегодня, " + formattedDate);

    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog, null);

        bottomSheetDialog.setContentView(bottomSheetView);
        Bundle bundle = new Bundle();
        bundle.putString("date", date);
        bundle.putString("formattedDate", formattedDate);

        Button addWalkButton = bottomSheetView.findViewById(R.id.addWalk);
        Button startWalkButton = bottomSheetView.findViewById(R.id.startWalk);

        if (bottomSheetDialog.getWindow() != null) {
            bottomSheetDialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        }

        addWalkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWalkFragment newFragment = new AddWalkFragment();
                newFragment.setArguments(bundle);
                fc.replaceFragment(newFragment);
                bottomSheetDialog.dismiss();
            }
        });

        startWalkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartWalkFragment newFragment = new StartWalkFragment();
                newFragment.setArguments(bundle);
                fc.replaceFragment(newFragment);
                bottomSheetDialog.dismiss();
            }
        });
        if (bottomSheetDialog.getWindow() != null) {
            bottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        }
        bottomSheetDialog.show();
    }
}