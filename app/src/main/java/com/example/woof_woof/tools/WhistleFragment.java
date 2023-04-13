package com.example.woof_woof.tools;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.woof_woof.R;
import com.example.woof_woof.databinding.FragmentWhistleBinding;


public class WhistleFragment extends Fragment {

    FragmentWhistleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWhistleBinding.inflate(inflater, container, false);
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext().getApplicationContext(), R.raw.whistle);
        binding.whistle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.whistle.setImageResource(R.drawable.svistok_act);
                    mediaPlayer.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.whistle.setImageResource(R.drawable.svistok_pas);
                }
                return true;
            }
        });
        return binding.getRoot();
    }
}