package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Switch;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button sButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sButton = findViewById(R.id.buttonS);
        final String url = "https://api.instagram.com/oauth/authorize\n" +
                "  ?client_id=598644611001645\n" +
                "  &redirect_uri=instagram.com\n" +
                "  &scope=user_profile,user_media\n" +
                "  &response_type=code";

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        GridView imageGrid = findViewById(R.id.image_grid);
        imageGrid.setAdapter(new ImageAdapter(getApplicationContext(), "https://picsum.photos/300", 11));
    }
}
