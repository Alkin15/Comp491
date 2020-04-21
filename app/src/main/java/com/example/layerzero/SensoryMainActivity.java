package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.layerzero.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SensoryMainActivity extends AppCompatActivity {
    DatabaseReference reff;
    Reflections reflection;
    long reflection_num;
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
        reflection = new Reflections();
        reflection.setBrief(response);

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
        reff.child(String.valueOf(reflection_num+1)).setValue(reflection);
        startActivity(intent);
    }
}
