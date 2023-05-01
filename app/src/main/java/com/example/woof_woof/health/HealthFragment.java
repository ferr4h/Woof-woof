package com.example.woof_woof.health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentHealthBinding;
import com.example.woof_woof.databinding.FragmentHomeBinding;
import com.example.woof_woof.home.ProfileFragment;


public class HealthFragment extends Fragment implements FragmentChangeListener {

    FragmentHealthBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentHealthBinding.inflate(inflater,  container, false);
        replaceFragment(new NotificationsFragment());
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