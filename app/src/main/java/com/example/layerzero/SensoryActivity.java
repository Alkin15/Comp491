package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;


import java.util.ArrayList;

public class SensoryActivity extends AppCompatActivity {
    SeekBar sensorySeekbar;
    int sensorySignificance=1;
    Button senButton;
    int step = 1;
    int max = 4;
    int min = 1;
    public static final String EXTRA_MESSAGE = "com.example.Survey.MESSAGE";
    EditText senResponse;
    public static final String responses = "responses";
    public static final String significace = "significance";
    ArrayList<String> allResponses = new ArrayList<String>();
    ArrayList<Integer> allSignificance = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensory);
        // Get the Intent that started this activity and extract the string
        final Intent intent = getIntent();
        final String message = intent.getStringExtra(SensoryMainActivity.EXTRA_MESSAGE);
        sensorySeekbar = (SeekBar) findViewById(R.id.sensorySeekbar);
        sensorySeekbar.setMax(max);
        allSignificance.add(sensorySignificance);
        sensorySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sensorySignificance = min+(progress*step);
                allSignificance.set(0,sensorySignificance);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        senButton = (Button) findViewById(R.id.sensoryNext);
        final Intent emotional = new Intent(this, EmotionalActivity.class);
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
        intent.putExtra(significace,allSignificance);
        this.startActivity(intent);
    }
}
