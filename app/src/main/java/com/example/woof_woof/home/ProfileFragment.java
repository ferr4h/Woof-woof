package com.example.woof_woof.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.widget.FrameLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.changeButton.setOnClickListener(this);
        return binding.getRoot();
    }
    @Override
    public void onClick(View view) {
        FragmentChangeListener fc = (FragmentChangeListener) getParentFragment();
        fc.replaceFragment(new ChangeProfileFragment());
    }
}