package com.example.assignmenttopsadvance.downloadFileFromUrl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignmenttopsadvance.databinding.ActivityDownloadFileBinding;


public class DownloadFileActivity extends AppCompatActivity {
    private ActivityDownloadFileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDownloadFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnDownload.setOnClickListener(v -> {
            Intent intent=new Intent(this,DownloadService.class);
            startService(intent);
        });
    }
}