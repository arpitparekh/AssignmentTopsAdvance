package com.example.assignmenttopsadvance.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assignmenttopsadvance.R;
import com.example.assignmenttopsadvance.databinding.ActivityAnimationHostBinding;

public class AnimationHostActivity extends AppCompatActivity {
    private ActivityAnimationHostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAnimationHostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}