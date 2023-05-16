package com.example.woof_woof.tools;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentToolsBinding;

public class ToolsFragment extends Fragment {

    FragmentToolsBinding binding;
    boolean isClicker=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentToolsBinding.inflate(inflater, container, false);
        replaceTool(new ClickerFragment());

        binding.clickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isClicker){
                    replaceTool(new ClickerFragment());
                    isClicker=true;
                }
            }
        });

        binding.whistleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicker) {
                    replaceTool(new WhistleFragment());
                    isClicker=false;
                }
            }
        });
        return binding.getRoot();
    }

    public void replaceTool(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.toolsLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}