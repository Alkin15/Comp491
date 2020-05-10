package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fba;
    private SpinKitView spinner;
    Boolean log = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PushNotifications.start(getApplicationContext(), "e8dc6bfa-81f3-4b61-ac38-aad27e4ef2e8");
        PushNotifications.addDeviceInterest("hello");
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, new PushNotificationReceivedListener() {
            @Override
            public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
                Intent i = new Intent(getApplicationContext(),SensoryMainActivity.class);
                startActivity(i);
            }
        });
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg =  token;
                        Log.d("TAG", msg);
                    }
                });

        PushNotifications.start(getApplicationContext(), "e8dc6bfa-81f3-4b61-ac38-aad27e4ef2e8");
        PushNotifications.addDeviceInterest("hello");
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, new PushNotificationReceivedListener() {
            @Override
            public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
                Intent i = new Intent(getApplicationContext(),SensoryMainActivity.class);
                startActivity(i);
            }
        });

        //Hide Spinner
        this.spinner = (SpinKitView) findViewById(R.id.spin_kit);
        spinner.setVisibility(View.GONE);
        fba = FirebaseAuth.getInstance();
        //Button Listener
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);

                View root = (View) v.getParent();

                //EditTexts
                String email = ((EditText) root.findViewById(R.id.email_text)).getText().toString();
                String password = ((EditText) root.findViewById(R.id.password_text)).getText().toString();

                Intent tutorial = new Intent(getApplicationContext(), BoardingActivity.class);
                startActivity(tutorial);

                //Firebase Login
                /*
                if(!email.isEmpty() && !password.isEmpty()){
                    fba.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //User logged in
                                spinner.setVisibility(View.GONE);
                                Intent tutorial = new Intent(getApplicationContext(), BoardingActivity.class);
                                startActivity(tutorial);
                            } else {
                                //failed to login
                                spinner.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, R.string.loginFailed, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    //Inputs not filled!
                    Toast.makeText(LoginActivity.this, R.string.fillMissingInformation, Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
    @Override
    protected void onPause() {

        super.onPause();

    }
    @Override
    protected void onResume() {
        super.onResume();
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, new PushNotificationReceivedListener() {
            @Override
            public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
                String messagePayload = remoteMessage.getData().get("inAppNotificationMessage");
                if (messagePayload == null) {
                    // Message payload was not set for this notification
                    Log.i("MyActivity", "Payload was missing");
                } else {
                    Intent i = new Intent(getApplicationContext(),SensoryMainActivity.class);
                    startActivity(i);
                    Log.i("MyActivity", messagePayload);
                    log = true;
                    // Now update the UI based on your message payload!
                }

            }
        });

    }
}
