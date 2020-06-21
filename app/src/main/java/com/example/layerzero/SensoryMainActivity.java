package com.example.layerzero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SensoryMainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.Survey.MESSAGE";

    public static final int PICK_IMAGE = 1;
    private Button galleryButton;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensory_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("imageUri")) {
            imageUri = extras.getString("imageUri");
        }
//        galleryButton = findViewById(R.id.gallery_button);

//        galleryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//            }
//        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if (requestCode == PICK_IMAGE) {
//
//        }
//    }


    public void sendMessage(View view){
        Intent intent = new Intent(this, SensoryActivity.class);
        EditText editText = (EditText) findViewById(R.id.briefResponse);
        String response = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,response);
        intent.putExtra("imageUri", imageUri);
        startActivity(intent);
    }
}
