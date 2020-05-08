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

        PushNotifications.start(getApplicationContext(), "e8dc6bfa-81f3-4b61-ac38-aad27e4ef2e8");
        PushNotifications.addDeviceInterest("hello");


    }

}
