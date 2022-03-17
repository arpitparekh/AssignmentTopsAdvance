package com.example.assignmenttopsadvance.socialMedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assignmenttopsadvance.databinding.ActivityTwitterBinding;

public class TwitterActivity extends AppCompatActivity {
    private ActivityTwitterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTwitterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}