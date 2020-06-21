package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;

public class IntellectualActivity extends AppCompatActivity {
    private static final java.util.UUID UUID = null;
    ImageView imageView;
    String imageUri;
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

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intellectual);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("imageUri")) {
            imageUri = extras.getString("imageUri");
        }
        final Intent intent = getIntent();
        lastmessage = intent.getStringArrayListExtra(EmotionalActivity.responses);
        intellectualButton = (Button) findViewById(R.id.intellectualNext);
        intellectualSeekbar = (SeekBar) findViewById(R.id.intellectualSeekbar);
        allsignificance = getIntent().getIntegerArrayListExtra(EmotionalActivity.significace);
        allsignificance.add(intellectualSignificance);
        intellectualSeekbar.setMax(max);
        intellectualSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                intellectualSignificance = min + (progress * step);
                allsignificance.set(2,intellectualSignificance);
                Toast.makeText(IntellectualActivity.this,"intellectualSigniicance=" + intellectualSignificance,Toast.LENGTH_LONG).show();
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

    private void uploadImage() {

        if(imageUri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(Uri.parse(imageUri))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(IntellectualActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(IntellectualActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void finish_survey(ArrayList<String> message,final Intent intent){
        intellectualResponse = (EditText) findViewById(R.id.intellectualResponse);
        String emResponse = intellectualResponse.getText().toString();
        message.add(emResponse);
        post = new Post();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(imageUri));
            imageView.setImageBitmap(bitmap);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        post.setPhotoURL(imageUri);
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
                    Toast.makeText(IntellectualActivity.this, "Failed to upload image!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
