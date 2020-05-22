package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.RemoteMessage;
import com.pusher.pushnotifications.PushNotificationReceivedListener;
import com.pusher.pushnotifications.PushNotifications;

import java.util.ArrayList;

import kotlin.reflect.jvm.internal.ReflectProperties;

public class HomeActivity extends AppCompatActivity {

    private Button sButton;
    private FirebaseAuth fba;
    private FirebaseDatabase db;
    Switch arSwitch;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        arSwitch = findViewById(R.id.switch1);
        arSwitch.setChecked(false);

        PushNotifications.start(getApplicationContext(), "e8dc6bfa-81f3-4b61-ac38-aad27e4ef2e8");
        PushNotifications.addDeviceInterest("hello");
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, new PushNotificationReceivedListener() {
            @Override
            public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
                Intent i = new Intent(getApplicationContext(),SensoryMainActivity.class);
                startActivity(i);
            }
        });



        //Spinner
        final com.github.ybq.android.spinkit.SpinKitView spinner = findViewById(R.id.spin_kit);
        spinner.setVisibility(View.GONE);

        //Get user data
        final ArrayList<Post> posts = new ArrayList<>();
        //TODO: activate after authentication fixed
        //fba = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();



        //String uid = fba.getCurrentUser().getUid();
        //change bottom line to dynamic
        DatabaseReference ref = db.getReference("users/000111/posts/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tmp : dataSnapshot.getChildren()){
                    posts.add(tmp.getValue(Post.class));
                }
                GridView imageGrid = findViewById(R.id.image_grid);
                imageGrid.setAdapter(new ImageAdapter(getApplicationContext(), posts));
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Log.v("tag", FirebaseInstanceId.getInstance().getInstanceId().toString());
        sButton = findViewById(R.id.buttonS);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), SensoryMainActivity.class);
                startActivity(i);

                /*
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.instagram.com/oauth/authorize?client_id=1383712118489523&redirect_uri=https://pdurak15.github.io/LayerZero-Website/&scope=user_profile,user_media&response_type=code"));
                startActivity(i);

                Intent i = new Intent(getApplicationContext(),instagramLogin.class);
                startActivity(i);

                 */

            }
        });


        arSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
//                if (isChecked) {
                arSwitch.setChecked(false);
                    Intent i = new Intent(getApplicationContext(), ARActivity.class);
                    startActivity(i);
//                }
            }
        });
    }
}
