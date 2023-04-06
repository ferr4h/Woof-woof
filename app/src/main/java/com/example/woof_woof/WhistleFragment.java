package com.example.woof_woof;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public class WhistleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_whistle, container, false);
        ImageView imageView = view.findViewById(R.id.whistle);
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext().getApplicationContext(), R.raw.whistle);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageView.setImageResource(R.drawable.svistok_act);
                    mediaPlayer.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imageView.setImageResource(R.drawable.svistok_pas);
                }
                return true;
            }
        });
        return view;
    }
}