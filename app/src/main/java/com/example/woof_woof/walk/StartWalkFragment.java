package com.example.woof_woof.walk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentAddWalkBinding;
import com.example.woof_woof.databinding.FragmentStartWalkBinding;

public class StartWalkFragment extends Fragment {

    FragmentStartWalkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStartWalkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}