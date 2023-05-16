package com.example.woof_woof.walk;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentAddWalkBinding;
import com.example.woof_woof.databinding.FragmentWalkCalendarBinding;
import com.example.woof_woof.health.NewNotificationFragment;
import com.example.woof_woof.health.NotificationsDatabase;
import com.example.woof_woof.health.NotificationsFragment;

public class AddWalkFragment extends Fragment implements View.OnClickListener{

    FragmentAddWalkBinding binding;
    String date, formattedDate;
    FragmentChangeListener fc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString("date");
            formattedDate = getArguments().getString("formattedDate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddWalkBinding.inflate(inflater, container, false);
        fc = (FragmentChangeListener) getParentFragment();
        binding.date.setText("Сегодня, "+formattedDate);
        binding.addButton.setOnClickListener(this);
        binding.cancelButton.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addButton:
                validateAndSave();
                break;
            case R.id.cancelButton:
                fc.replaceFragment(new WalkCalendarFragment());
                break;
        }
    }
    void validateAndSave(){

        String hoursStart = binding.hoursStart.getText().toString().trim();
        String minutesStart = binding.minutesStart.getText().toString().trim();
        String hoursEnd = binding.hoursEnd.getText().toString().trim();
        String minutesEnd = binding.minutesEnd.getText().toString().trim();

        if (hoursStart.isEmpty() || minutesStart.isEmpty() || hoursEnd.isEmpty() || minutesEnd.isEmpty()) {
            Toast.makeText(getActivity(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (hoursStart.length()<2 || minutesStart.length()<2 || hoursEnd.length()<2 || minutesEnd.length()<2){
            Toast.makeText(getActivity(), "Поля должны быть заполнены в фромате HH/MM", Toast.LENGTH_SHORT).show();
            return;
        }

        int hoursStInt = Integer.parseInt(hoursStart);
        int minutesStInt = Integer.parseInt(minutesStart);
        int hoursEndInt= Integer.parseInt(hoursEnd);
        int minutesEndInt = Integer.parseInt(minutesEnd);

        if (hoursStInt<0 || hoursStInt>23 ||
                minutesStInt<0 || minutesStInt>59 ||
                hoursEndInt<0 || hoursEndInt>23 ||
                minutesEndInt<0 || minutesEndInt>59){
            Toast.makeText(getActivity(), "Данные введены неверно", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = (hoursEndInt-hoursStInt)*60+(minutesEndInt-minutesStInt);
        if (duration<=0){
            Toast.makeText(getActivity(), "Время конца должно быть больше времени начала", Toast.LENGTH_SHORT).show();
            return;
        }

        WalksDatabase db = new WalksDatabase(getContext());
        db.addWalk(date,
                hoursStart + ":" + minutesStart,
                hoursEnd + ":" + minutesEnd,
                String.valueOf(duration));

        fc.replaceFragment(new WalkCalendarFragment());

    }
}