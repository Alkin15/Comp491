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

    Socket s;
    DataOutputStream dos;
    protected String serverAddress;
    protected int serverPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            String ip = Inet4Address.getByName("13.58.235.73").getCanonicalHostName();
            s = new Socket(ip,4444);
            Log.d("Server","Connected to server");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        PushNotifications.start(getApplicationContext(), "a1857535-3ff2-4bfa-8ccf-1a78f47913e7");
        PushNotifications.addDeviceInterest("hello");

    }

}
