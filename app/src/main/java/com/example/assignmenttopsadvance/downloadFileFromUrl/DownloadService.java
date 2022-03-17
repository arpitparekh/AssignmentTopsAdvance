package com.example.assignmenttopsadvance.downloadFileFromUrl;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void downloadFiles() {
        new Thread(() -> {
            try {

                URL u = new URL("https://file-examples-com.github.io/uploads/2017/10/file_example_JPG_100kB.jpg");

                // start data input stream

                InputStream is = u.openStream();
                DataInputStream dis = new DataInputStream(is);

                byte[] buffer = new byte[1024];
                int length;

                //main code
                FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/"+"test.jpg"));
                while ((length = dis.read(buffer))>0) {
                    fos.write(buffer, 0, length);
                }
                is.close();
                fos.close();

            } catch (MalformedURLException mue) {
                Log.e("SYNC getUpdate", "malformed url error", mue);
            } catch (IOException ioe) {
                Log.e("SYNC getUpdate", "io error", ioe);
            } catch (SecurityException se) {
                Log.e("SYNC getUpdate", "security error", se);
            }

        }).start();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        downloadFiles();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {

        return super.stopService(name);
    }
}
