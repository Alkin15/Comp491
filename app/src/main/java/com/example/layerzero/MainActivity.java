package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;
import com.pusher.pushnotifications.PushNotifications;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushNotifications.start(getApplicationContext(), "a1857535-3ff2-4bfa-8ccf-1a78f47913e7");
        PushNotifications.addDeviceInterest("hello");

    }

}
