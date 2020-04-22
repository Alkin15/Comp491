package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class emotionalActivity extends AppCompatActivity {
    Button emotionalButton;
    EditText emotionalResponse;
    ArrayList<String> message = new ArrayList<String>();
    public static final String responses = "responses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotional);

        final Intent intent = getIntent();
        message = intent.getStringArrayListExtra(sensoryActivity.responses);
        emotionalButton = (Button) findViewById(R.id.emotionalNext);
        final Intent intellectual = new Intent(this,intellectualActivity.class);
        emotionalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_intent(message,intellectual);
            }
        });
    }

    public void start_intent(ArrayList<String> message,Intent intent){

        emotionalResponse = (EditText) findViewById(R.id.emotionalResponse);
        String emResponse = emotionalResponse.getText().toString();
        message.add(emResponse);
        intent.putExtra(responses, message);
        this.startActivity(intent);
    }
}
