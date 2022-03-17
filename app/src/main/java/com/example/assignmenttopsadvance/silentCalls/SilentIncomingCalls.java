package com.example.assignmenttopsadvance.silentCalls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assignmenttopsadvance.databinding.ActivitySilentIncomingCallsBinding;

public class SilentIncomingCalls extends AppCompatActivity {
    private ActivitySilentIncomingCallsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySilentIncomingCallsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}