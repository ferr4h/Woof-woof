package com.example.woof_woof;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.woof_woof.databinding.ActivityMainBinding;
import com.example.woof_woof.health.HealthFragment;
import com.example.woof_woof.home.HomeFragment;
import com.example.woof_woof.tools.ToolsFragment;
import com.example.woof_woof.walk.WalkFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String isFirst = sharedPreferences.getString("isFirst", "null");

        if (isFirst=="null"){
            replaceFragment(new RegistrationFragment());
            binding.bottomNavigationView.setVisibility(View.GONE);
        }
        else{
            replaceFragment(new WalkFragment());
        }

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
    public void continueAfterRegistration(){
        replaceFragment(new WalkFragment());
        binding.bottomNavigationView.setVisibility(View.VISIBLE);
    }
}