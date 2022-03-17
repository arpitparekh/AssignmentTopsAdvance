package com.example.assignmenttopsadvance.alarm_manager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.assignmenttopsadvance.databinding.ActivityAlarmBinding;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    private ActivityAlarmBinding binding;
    AlarmManager manager;
    PendingIntent pendingIntent;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager= (AlarmManager) getSystemService(ALARM_SERVICE);    //do not forget this

        binding.toggleOnOff.setOnClickListener(v -> {
            long time;
            if(binding.toggleOnOff.isChecked()){

                Toast.makeText(this, "Alarm On", Toast.LENGTH_SHORT).show();

                Calendar calendar=Calendar.getInstance();

                if(calendar!=null){
                    calendar.set(Calendar.HOUR_OF_DAY,binding.timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE,binding.timePicker.getMinute());
                }



                Intent intent=new Intent(this,AlarmReceiver.class);

                pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

                //time format as AM and PM

                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    // setting time as AM and PM
                    if (calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }
                manager.setRepeating(AlarmManager.RTC_WAKEUP,time,10000,pendingIntent);
            }else{
                manager.cancel(pendingIntent);
                Toast.makeText(this, "Alarm is Off", Toast.LENGTH_SHORT).show();
            }
        });



    }
}