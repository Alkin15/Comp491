package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class sensoryActivity extends AppCompatActivity {
    Button senButton;
    public static final String EXTRA_MESSAGE = "com.example.Survey.MESSAGE";
    EditText senResponse;
    public static final String responses = "responses";
    ArrayList<String> allResponses = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensory);

        // Get the Intent that started this activity and extract the string
        final Intent intent = getIntent();
        final String message = intent.getStringExtra(SensoryMainActivity.EXTRA_MESSAGE);
        senButton = (Button) findViewById(R.id.sensoryNext);
        final Intent emotional = new Intent(this,emotionalActivity.class);
        senButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_intent(message,emotional);

            }

        });
    }
    public void start_intent(String message,Intent intent){

        senResponse = (EditText) findViewById(R.id.sensoryResponse);
        String sensoryResponse = senResponse.getText().toString();
        allResponses.add(message);
        allResponses.add(sensoryResponse);
        intent.putExtra(responses, allResponses);
        this.startActivity(intent);
    }
}
