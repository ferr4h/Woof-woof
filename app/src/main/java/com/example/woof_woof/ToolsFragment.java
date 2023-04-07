package com.example.woof_woof;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.databinding.FragmentToolsBinding;

public class ToolsFragment extends Fragment {

    FragmentToolsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentToolsBinding.inflate(inflater, container, false);
        replaceTool(new ClickerFragment());

        binding.clickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceTool(new ClickerFragment());
            }
        });

        binding.whistleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceTool(new WhistleFragment());
            }
        });
        return binding.getRoot();
    }

    public void replaceTool(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.toolsLayout, fragment);
        fragmentTransaction.commit();
    }
}