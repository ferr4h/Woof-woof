package com.example.woof_woof.health;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentAddNotificationBinding;
import com.example.woof_woof.databinding.FragmentNewNotificationBinding;

public class AddNotificationFragment extends Fragment implements View.OnClickListener{

    FragmentAddNotificationBinding binding;
    String notificationType;
    FragmentChangeListener fc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notificationType = getArguments().getString("notificationType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= FragmentAddNotificationBinding.inflate(inflater, container, false);
        fc = (FragmentChangeListener) getParentFragment();
        binding.title.setText(notificationType);
        binding.createButton.setOnClickListener(this);
        binding.backButton.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createButton:
                fc.replaceFragment(new NotificationsFragment());
                break;
            case R.id.backButton:
                fc.replaceFragment(new NewNotificationFragment());
                break;
        }
    }
}