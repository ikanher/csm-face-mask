package com.example.atry;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyBroadcastReceiver";
    private Button btnStartService, btnStopService;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartService = findViewById(R.id.buttonStartService);
        btnStopService = findViewById(R.id.buttonStopService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startService();
                } catch (Exception e) {
                    System.err.println("Failed to start service:" + e.toString());
                    System.exit(42);
                }
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        }, 3);
    }
    public void startService() throws IOException {
        Log.d(TAG, "Service starting.");
        try {
            Intent serviceIntent = new Intent(this, ForegroundService.class);
            serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android.");
            ContextCompat.startForegroundService(this, serviceIntent);
        } catch (Exception e) {
            throw e;
        }
    }

    public void stopService() {
        Log.d(TAG, "Service stopping.");
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }
}