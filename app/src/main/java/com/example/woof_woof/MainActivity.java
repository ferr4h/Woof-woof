package com.example.woof_woof;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.woof_woof.databinding.ActivityMainBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new WalkFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.walk:
                    replaceFragment(new WalkFragment());
                    break;
                case R.id.health:
                    replaceFragment(new HealthFragment());
                    break;
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.tools:
                    replaceFragment(new ToolsFragment());
                    break;
                case R.id.training:
                    replaceFragment(new TrainingFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}