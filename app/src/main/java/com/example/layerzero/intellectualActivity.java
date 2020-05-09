package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class intellectualActivity extends AppCompatActivity {
    SeekBar intellectualSeekbar;
    int intellectualSignificance=1;
    Button intellectualButton;
    public static final String significace = "significance";
    int step = 1;
    int max = 4;
    int min = 1;
    EditText intellectualResponse;
    ArrayList<String> lastmessage = new ArrayList<String>();
    ArrayList<Integer> allsignificance = new ArrayList<Integer>();
    Post post;
    long post_count;
    DatabaseReference reff;
    public static final String responses = "responses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellectual);
        final Intent intent = getIntent();
        lastmessage = intent.getStringArrayListExtra(emotionalActivity.responses);
        intellectualButton = (Button) findViewById(R.id.intellectualNext);
        intellectualSeekbar = (SeekBar) findViewById(R.id.intellectualSeekbar);
        allsignificance = getIntent().getIntegerArrayListExtra(emotionalActivity.significace);
        allsignificance.add(intellectualSignificance);
        intellectualSeekbar.setMax(max);
        intellectualSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intellectualSignificance = min + (progress * step);
                allsignificance.set(2,intellectualSignificance);
                Toast.makeText(intellectualActivity.this,"intellectualSigniicance=" + intellectualSignificance,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final Intent intellectual = new Intent(this, HomeActivity.class);

        reff = FirebaseDatabase.getInstance().getReference("users/" + "000111" + "/posts/");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    post_count = dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        intellectualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish_survey(lastmessage,intellectual);
            }
        });

    }

    public void finish_survey(ArrayList<String> message,final Intent intent){
        intellectualResponse = (EditText) findViewById(R.id.intellectualResponse);
        String emResponse = intellectualResponse.getText().toString();
        message.add(emResponse);
        post = new Post();
        post.setPhotoURL("https://thumbnailer.mixcloud.com/unsafe/300x300/extaudio/5/d/3/4/a4f7-f879-436c-834b-e72605bd3f2f.jpg");
        post.setSensoryPoint(allsignificance.get(0).toString());
        post.setIntellectualPoint(allsignificance.get(2).toString());
        post.setEmotionalPoint(allsignificance.get(1).toString());
        post.setBriefDescription(message.get(0));
        post.setSensoryDescription(message.get(1));
        post.setEmotionalDescription(message.get(2));
        post.setIntellectualDescription(message.get(3));
        reff.child(String.valueOf(post_count +1)).setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(intent);
                } else {
                    Toast.makeText(intellectualActivity.this, "Failed to upload image!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
