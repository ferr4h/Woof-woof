package com.example.woof_woof.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    FragmentProfileBinding binding;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        loadData();
        binding.changeButton.setOnClickListener(this);

        return binding.getRoot();
    }
    @Override
    public void onClick(View view) {
        FragmentChangeListener fc = (FragmentChangeListener) getParentFragment();
        fc.replaceFragment(new ChangeProfileFragment());
    }
    public void loadData(){
        sharedPreferences=getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String years = sharedPreferences.getString("YEARS", "3");
        String months = sharedPreferences.getString("MONTHS", "10");
        binding.nameText.setText(sharedPreferences.getString("NAME", "Борис"));
        binding.ageText.setText("Годы "+years+"  Месяцы " + months);
        binding.breedText.setText(sharedPreferences.getString("BREED", "Американский стаффордширский йорктерьер"));
        binding.sexText.setText(sharedPreferences.getString("SEX", "M"));
        String imageUri = sharedPreferences.getString("PROFILE_IMAGE_URI", "");
        /*if (!imageUri.isEmpty()) {
           binding.profileImage.setImageURI(Uri.parse(imageUri));
           binding.profileImage.setImageBitmap(getCircleBitmap(binding.profileImage));
        }*/
    }

}