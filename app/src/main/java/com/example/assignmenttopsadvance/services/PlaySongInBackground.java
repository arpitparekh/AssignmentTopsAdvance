package com.example.assignmenttopsadvance.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignmenttopsadvance.databinding.ActivityPlaySongInBackgroundBinding;

public class PlaySongInBackground extends AppCompatActivity {
    private ActivityPlaySongInBackgroundBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPlaySongInBackgroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStartInBackGround.setOnClickListener(v -> {
            Intent intent=new Intent(this,BackgroundSongService.class);
            startService(intent);
        });
    }
}