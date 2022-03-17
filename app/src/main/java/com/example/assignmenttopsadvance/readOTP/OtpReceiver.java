package com.example.assignmenttopsadvance.readOTP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class OtpReceiver extends BroadcastReceiver {
    private static TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        SmsMessage[] messages= Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for(SmsMessage sms:messages){
            String message=sms.getMessageBody();
            String otp=message.split(": ")[1];
            textView.setText(otp);
        }
    }

    public void setText(TextView tvOtp) {

        OtpReceiver.textView=tvOtp;

    }
}
