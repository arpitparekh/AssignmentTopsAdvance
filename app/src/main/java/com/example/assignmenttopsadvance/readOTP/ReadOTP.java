package com.example.assignmenttopsadvance.readOTP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.assignmenttopsadvance.databinding.ActivityReadOtpBinding;

public class ReadOTP extends AppCompatActivity {
    private ActivityReadOtpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityReadOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkPermission();

        new OtpReceiver().setText(binding.tvOtp);
    }

    private void checkPermission() {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},1);
        }
    }
}