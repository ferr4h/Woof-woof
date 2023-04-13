package com.example.woof_woof.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentChangeProfileBinding;

public class ChangeProfileFragment extends Fragment implements View.OnClickListener{

    FragmentChangeProfileBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentChangeProfileBinding.inflate(inflater, container, false);
        binding.saveButton.setOnClickListener(this);
        binding.backArrow.setOnClickListener(this);
        return binding.getRoot();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
                validateAndSave();
                break;
            case R.id.backArrow:
                goBack();
                break;
        }
    }
    private void validateAndSave() {
        String name = binding.nameField.getText().toString().trim();
        String breed = binding.breedField.getText().toString().trim();
        String years=binding.yearsField.getText().toString().trim();
        String months=binding.monthsField.getText().toString().trim();
        boolean maleChecked = binding.rbMale.isChecked();
        boolean femaleChecked = binding.rbFemale.isChecked();

        if (name.isEmpty() || breed.isEmpty() || years.isEmpty() || months.isEmpty() || (!maleChecked && !femaleChecked)) {
            Toast.makeText(getActivity(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int year = Integer.parseInt(years);
        int month = Integer.parseInt(months);

        if (year>30) year=30;
        if (month>=12){
            year++;
            month=0;
        }

        years=Integer.toString(year);
        months=Integer.toString(month);

        sharedPreferences = getActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("NAME", name);
        editor.putString("BREED", breed);
        editor.putString("YEARS", years);
        editor.putString("MONTHS", months);
        editor.putString("SEX", maleChecked ? "M" : "F");
        editor.apply();

        Toast.makeText(getActivity(), "Данные сохранены", Toast.LENGTH_SHORT).show();


        FragmentChangeListener fc = (FragmentChangeListener) getParentFragment();
        fc.replaceFragment(new ProfileFragment());
    }

    public void goBack(){
        FragmentChangeListener fc = (FragmentChangeListener) getParentFragment();
        fc.replaceFragment(new ProfileFragment());
    }
}