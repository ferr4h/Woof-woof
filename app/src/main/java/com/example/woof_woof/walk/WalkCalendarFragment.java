package com.example.woof_woof.walk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentWalkBinding;
import com.example.woof_woof.databinding.FragmentWalkCalendarBinding;

import java.util.Calendar;

public class WalkCalendarFragment extends Fragment {

    FragmentWalkCalendarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWalkCalendarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}