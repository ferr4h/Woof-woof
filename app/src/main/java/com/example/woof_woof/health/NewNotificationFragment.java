package com.example.woof_woof.health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentNewNotificationBinding;

public class NewNotificationFragment extends Fragment implements View.OnClickListener {

    FragmentNewNotificationBinding binding;
    Bundle bundle;
    AddNotificationFragment newFragment;
    FragmentChangeListener fc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentNewNotificationBinding.inflate(inflater, container, false);
        bundle = new Bundle();
        newFragment = new AddNotificationFragment();
        fc = (FragmentChangeListener) getParentFragment();
        binding.bathButton.setOnClickListener(this);
        binding.vaccineButton.setOnClickListener(this);
        binding.scissorsButton.setOnClickListener(this);
        binding.miteButton.setOnClickListener(this);
        binding.wormButton.setOnClickListener(this);
        binding.earButton.setOnClickListener(this);
        binding.vetButton.setOnClickListener(this);
        binding.toothButton.setOnClickListener(this);
        binding.cancelButton.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bathButton:
                bundle.putString("notificationType", getString(R.string.bath));
                goToAddFragment();
                break;
            case R.id.vaccineButton:
                bundle.putString("notificationType", getString(R.string.vaccine));
                goToAddFragment();
                break;
            case R.id.scissorsButton:
                bundle.putString("notificationType", getString(R.string.scissors));
                goToAddFragment();
                break;
            case R.id.miteButton:
                bundle.putString("notificationType", getString(R.string.mite));
                goToAddFragment();
                break;
            case R.id.wormButton:
                bundle.putString("notificationType", getString(R.string.worm));
                goToAddFragment();
                break;
            case R.id.earButton:
                bundle.putString("notificationType", getString(R.string.ear));
                goToAddFragment();
                break;
            case R.id.vetButton:
                bundle.putString("notificationType", getString(R.string.vet));
                goToAddFragment();
                break;
            case R.id.toothButton:
                bundle.putString("notificationType", getString(R.string.tooth));
                goToAddFragment();
                break;
            case R.id.cancelButton:
                fc.replaceFragment(new NotificationsFragment());
                break;
        }
    }
    public void goToAddFragment(){
        newFragment.setArguments(bundle);
        fc.replaceFragment(newFragment);
    }
}