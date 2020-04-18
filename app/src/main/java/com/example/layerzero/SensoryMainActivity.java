package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.layerzero.R;

public class SensoryMainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.Survey.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensory_main);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, sensoryActivity.class);
        EditText editText = (EditText) findViewById(R.id.briefResponse);
        String response = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,response);
        startActivity(intent);
    }
}
