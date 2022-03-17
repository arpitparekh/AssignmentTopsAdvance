package com.example.assignmenttopsadvance.socialMedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assignmenttopsadvance.databinding.ActivityFacebookBinding;

public class FacebookActivity extends AppCompatActivity {
    private ActivityFacebookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFacebookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}