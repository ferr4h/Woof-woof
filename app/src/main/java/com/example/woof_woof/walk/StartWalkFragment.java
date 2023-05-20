package com.example.woof_woof.walk;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentAddWalkBinding;
import com.example.woof_woof.databinding.FragmentStartWalkBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StartWalkFragment extends Fragment implements View.OnClickListener{

    FragmentStartWalkBinding binding;
    String date, formattedDate, start, end, duration;
    FragmentChangeListener fc;
    boolean isRunning=false;
    Chronometer ch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString("date");
            formattedDate = getArguments().getString("formattedDate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStartWalkBinding.inflate(inflater, container, false);
        fc = (FragmentChangeListener) getParentFragment();
        ch=binding.chronometer;
        binding.date.setText(formattedDate);
        binding.startButton.setOnClickListener(this);
        binding.cancelButton.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                startChrono();
                break;
            case R.id.cancel_button:
                cancel();
                break;
        }
    }
    void startChrono() {
        if (!isRunning) {
            binding.startButton.setText(getResources().getString(R.string.stop_walk));
            binding.startButton.setBackgroundResource(R.drawable.red_button);
            start = getTime();
            ch.setBase(SystemClock.elapsedRealtime());
            ch.start();
            isRunning = true;
        } else {
            long elapsedTime = SystemClock.elapsedRealtime() - ch.getBase();
            ch.stop();
            end = getTime();
            duration = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % 60);
            uploadData();
            fc.replaceFragment(new WalkCalendarFragment());
        }
    }
    void cancel(){
        ch.setBase(SystemClock.elapsedRealtime());
        fc.replaceFragment(new WalkCalendarFragment());
    }

    void uploadData(){
        WalksDatabase db = new WalksDatabase(getContext());
        db.addWalk(date, start, end, duration);
    }
    String getTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour+":"+minute;
    }
}