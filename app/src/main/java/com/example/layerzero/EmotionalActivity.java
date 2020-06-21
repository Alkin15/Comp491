package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.ArrayList;

public class EmotionalActivity extends AppCompatActivity {
    String imageUri;
    SeekBar emotionalSeekbar;
    int emotionalSignificance=1;
    Button emotionalButton;
    EditText emotionalResponse;
    ArrayList<String> message = new ArrayList<String>();
    public static final String significace = "significance";
    ArrayList<Integer> allsignificance = new ArrayList<Integer>();
    int step = 1;
    int max = 4;
    int min = 1;
    public static final String responses = "responses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("imageUri")) {
            imageUri = extras.getString("imageUri");
        }
        setContentView(R.layout.activity_emotional);
        allsignificance = getIntent().getIntegerArrayListExtra(significace);
        allsignificance.add(emotionalSignificance);
        emotionalSeekbar = (SeekBar) findViewById(R.id.emotionalSeekbar);
        emotionalSeekbar.setMax(max);
        emotionalSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                emotionalSignificance = min+(progress*step);
                allsignificance.set(1,emotionalSignificance);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final Intent intent = getIntent();
        message = intent.getStringArrayListExtra(SensoryActivity.responses);
        emotionalButton = (Button) findViewById(R.id.emotionalNext);
        final Intent intellectual = new Intent(this, IntellectualActivity.class);
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
        intent.putExtra(significace,allsignificance);
        intent.putExtra("imageUri", imageUri);
        this.startActivity(intent);
    }
}
