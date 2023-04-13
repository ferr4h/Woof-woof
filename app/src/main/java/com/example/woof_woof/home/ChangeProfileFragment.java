package com.example.woof_woof.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentChangeProfileBinding;

public class ChangeProfileFragment extends Fragment implements View.OnClickListener{

    FragmentChangeProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentChangeProfileBinding.inflate(inflater, container, false);
        binding.saveButton.setOnClickListener(this);
        return binding.getRoot();
    }
    @Override
    public void onClick(View view) {
        FragmentChangeListener fc = (FragmentChangeListener) getParentFragment();
        fc.replaceFragment(new ProfileFragment());
    }
}