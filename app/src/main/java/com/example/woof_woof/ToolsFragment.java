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

public class ToolsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tools, container, false);
        Button setClickerButton = view.findViewById(R.id.clicker_button);
        Button setWhistleButton = view.findViewById(R.id.whistle_button);

        replaceTool(new ClickerFragment());

        setClickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceTool(new ClickerFragment());
            }
        });

        setWhistleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceTool(new WhistleFragment());
            }
        });
        return view;
    }

    public void replaceTool(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.toolsLayout, fragment);
        fragmentTransaction.commit();
    }
}