package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class intellectualActivity extends AppCompatActivity {
    Button intellectualButton;
    EditText intellectualResponse;
    ArrayList<String> lastmessage = new ArrayList<String>();
    Reflections reflection;
    long reflection_num;
    DatabaseReference reff;
    public static final String responses = "responses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellectual);
        final Intent intent = getIntent();
        lastmessage = intent.getStringArrayListExtra(sensoryActivity.responses);
        intellectualButton = (Button) findViewById(R.id.intellectualNext);
        final Intent intellectual = new Intent(this,intellectualActivity.class);
        final Intent main = new Intent(this,HomeActivity.class);
        reff = FirebaseDatabase.getInstance().getReference().child("Reflections");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    reflection_num = dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        intellectualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish_survey(lastmessage,intellectual,main);
            }
        });

    }

    public void finish_survey(ArrayList<String> message,Intent intent,Intent main){

        intellectualResponse = (EditText) findViewById(R.id.intellectualResponse);
        String emResponse = intellectualResponse.getText().toString();
        message.add(emResponse);
        reflection = new Reflections();
        reflection.setBrief(message.get(0));
        reflection.setSensory(message.get(1));
        reflection.setEmotional(message.get(2));
        reflection.setIntellectual(message.get(3));
        reff.child(String.valueOf(reflection_num+1)).setValue(reflection);
        this.startActivity(main);

    }

}
