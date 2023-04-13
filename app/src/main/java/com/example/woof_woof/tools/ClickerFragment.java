package com.example.woof_woof.tools;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.view.View;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentClickerBinding;

public class ClickerFragment extends Fragment {

    FragmentClickerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentClickerBinding.inflate(inflater, container, false);
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext().getApplicationContext(), R.raw.click);
        binding.clicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.clicker.setImageResource(R.drawable.clicker__click);
                    mediaPlayer.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.clicker.setImageResource(R.drawable.clicker);
                }
                return true;
            }
        });
        return binding.getRoot();
    }
}