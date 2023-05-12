package com.example.woof_woof.health;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentEditNotificationBinding;

public class EditNotificationFragment extends Fragment implements View.OnClickListener{

    FragmentEditNotificationBinding binding;
    String id, type, dayInput, monthInput, hoursInput, minutesInput, descriptionInput;
    FragmentChangeListener fc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentEditNotificationBinding.inflate(inflater, container, false);
        fc = (FragmentChangeListener) getParentFragment();
        getData();
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updateButton:
                validateAndSave();
                break;
            case R.id.deleteButton:
                fc.replaceFragment(new NotificationsFragment());
                break;
        }
    }
    void getData(){
        binding.dayUpd.setText(dayInput);
        binding.monthUpd.setText(monthInput);
        binding.hoursUpd.setText(hoursInput);
        binding.minutesUpd.setText(minutesInput);
        binding.descriptionUpd.setText(descriptionInput);
    }
    private void validateAndSave() {

        String dayStr = binding.dayUpd.getText().toString().trim();
        String monthStr = binding.monthUpd.getText().toString().trim();
        String hoursStr = binding.hoursUpd.getText().toString().trim();
        String minutesStr = binding.minutesUpd.getText().toString().trim();
        String description = binding.descriptionUpd.getText().toString().trim();

        if (dayStr.isEmpty() || monthStr.isEmpty() || hoursStr.isEmpty() || minutesStr.isEmpty()) {
            Toast.makeText(getActivity(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        int day = Integer.parseInt(dayStr);
        int month = Integer.parseInt(monthStr);
        int hours = Integer.parseInt(hoursStr);
        int minutes = Integer.parseInt(minutesStr);

        String formattedDay = String.format("%02d", day);
        String formattedMonth = String.format("%02d", month);
        String formattedHours = String.format("%02d", hours);
        String formattedMinutes = String.format("%02d", minutes);

        if (day > 31 || day < 1 ||
                month > 12 || month < 1 ||
                hours > 23 || hours < 0 ||
                minutes > 59 || minutes < 0) {
            Toast.makeText(getActivity(), "Данные введены неверно", Toast.LENGTH_SHORT).show();
            return;
        }

        NotificationsDatabase db = new NotificationsDatabase(getContext());
        db.updateData(id,
                type,
                formattedDay + "." + formattedMonth,
                formattedHours + ":" + formattedMinutes,
                description);
        fc.replaceFragment(new NotificationsFragment());
    }
}