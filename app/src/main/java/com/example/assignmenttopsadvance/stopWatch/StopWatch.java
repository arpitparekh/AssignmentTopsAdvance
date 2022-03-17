package com.example.assignmenttopsadvance.stopWatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.example.assignmenttopsadvance.databinding.ActivityStopWatchBinding;

public class StopWatch extends AppCompatActivity {
    private ActivityStopWatchBinding binding;
    private int second=0;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStopWatchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        runTimer();

        binding.btnStart.setOnClickListener(v -> {
            running=true;
        });
        binding.btnStop.setOnClickListener(v -> {
            running=false;
        });
        binding.btnReset.setOnClickListener(v -> {
            running=false;
            second=0;
        });
    }


    private void runTimer() {
        Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=second/3600;
                int minutes=(second%3600)/60;
                int secs=(second%60);

                //formatting the textView According to our liking
                String time=String.format("%02d : %02d : %02d",hours,minutes,secs);
                binding.tvTimeStopWatch.setText(time);
                if(running){
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}