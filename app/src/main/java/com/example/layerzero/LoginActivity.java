package com.example.layerzero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fba;
    private SpinKitView spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide Spinner
        this.spinner = (SpinKitView) findViewById(R.id.spin_kit);
        spinner.setVisibility(View.GONE);

        fba = FirebaseAuth.getInstance();

        //Button Listener
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);

                View root = (View) v.getParent();

                //EditTexts
                String email = ((EditText) root.findViewById(R.id.email_text)).getText().toString();
                String password = ((EditText) root.findViewById(R.id.password_text)).getText().toString();

                Intent tutorial = new Intent(getApplicationContext(), BoardingActivity.class);
                startActivity(tutorial);

                //Firebase Login
                /*
                if(!email.isEmpty() && !password.isEmpty()){
                    fba.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                //User logged in
                                spinner.setVisibility(View.GONE);
                                Intent tutorial = new Intent(getApplicationContext(), BoardingActivity.class);
                                startActivity(tutorial);
                            } else {
                                //failed to login
                                spinner.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, R.string.loginFailed, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    //Inputs not filled!
                    Toast.makeText(LoginActivity.this, R.string.fillMissingInformation, Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}
