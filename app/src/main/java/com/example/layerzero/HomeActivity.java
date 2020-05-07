package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button sButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.v("tag", FirebaseInstanceId.getInstance().getInstanceId().toString());
        sButton = findViewById(R.id.buttonS);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SensoryMainActivity.class);
                startActivity(i);
            }
        });

        GridView imageGrid = findViewById(R.id.image_grid);
        imageGrid.setAdapter(new ImageAdapter(getApplicationContext(), "https://picsum.photos/300", 11));
    }
}
