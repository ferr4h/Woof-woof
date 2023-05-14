package com.example.woof_woof.walk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentHomeBinding;
import com.example.woof_woof.databinding.FragmentWalkBinding;
import com.example.woof_woof.databinding.FragmentWhistleBinding;
import com.example.woof_woof.home.ProfileFragment;

public class WalkFragment extends Fragment implements FragmentChangeListener {

    FragmentWalkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWalkBinding.inflate(inflater, container, false);
        replaceFragment(new WalkCalendarFragment());
        return binding.getRoot();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentLayout, fragment);
        ft.commit();
    }
}