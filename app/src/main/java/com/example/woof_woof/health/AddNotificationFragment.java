package com.example.woof_woof.health;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.woof_woof.FragmentChangeListener;
import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentAddNotificationBinding;
import com.example.woof_woof.databinding.FragmentNewNotificationBinding;

import java.util.Objects;

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
        binding = FragmentAddNotificationBinding.inflate(inflater, container, false);
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
                validateAndSave();
                break;
            case R.id.backButton:
                fc.replaceFragment(new NewNotificationFragment());
                break;
        }
    }
    private void validateAndSave() {
        String dayStr = binding.day.getText().toString().trim();
        String monthStr = binding.month.getText().toString().trim();
        String hoursStr = binding.hours.getText().toString().trim();
        String minutesStr = binding.minutes.getText().toString().trim();
        String description = binding.description.getText().toString().trim();

        if (dayStr.isEmpty() || monthStr.isEmpty() || hoursStr.isEmpty() || minutesStr.isEmpty()) {
            Toast.makeText(getActivity(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dayStr.length()<2 || monthStr.length()<2 || hoursStr.length()<2 || minutesStr.length()<2){
            Toast.makeText(getActivity(), "Поля должны быть заполнены в фромате DD/MM и HH/MM", Toast.LENGTH_SHORT).show();
            return;
        }

        int day = Integer.parseInt(dayStr);
        int month = Integer.parseInt(monthStr);
        int hours = Integer.parseInt(hoursStr);
        int minutes = Integer.parseInt(minutesStr);

        if (day > 31 || day < 1 ||
                month > 12 || month < 1 ||
                hours > 23 || hours < 0 ||
                minutes > 59 || minutes < 0) {
            Toast.makeText(getActivity(), "Данные введены неверно", Toast.LENGTH_SHORT).show();
            return;
        }

        NotificationsDatabase db = new NotificationsDatabase(getContext());
        db.addNotification(notificationType,
                dayStr + "." + monthStr,
                hoursStr + ":" + minutesStr,
                description);
        createNotificationChannel();
        NotificationScheduler.scheduleNotification(requireContext(), notificationType, day, month, hours, minutes);
        fc.replaceFragment(new NotificationsFragment());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "default";
            String description = "Default channel for notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("default", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = requireActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}