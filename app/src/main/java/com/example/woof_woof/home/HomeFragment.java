package com.example.woof_woof.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements FragmentChangeListener{

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        replaceFragment(new ProfileFragment());
        return binding.getRoot();
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentLayout, fragment);
        ft.commit();
    }
}