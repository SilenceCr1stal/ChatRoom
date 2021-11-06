package com.example.chatroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrationPage extends AppCompatActivity {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("users");

    public static String username = "unknown";

    Button btn2_signup;
    EditText user_email, pass_word, user_name;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        user_email = findViewById(R.id.email);
        pass_word = findViewById(R.id.password);
        user_name = findViewById(R.id.username);
        btn2_signup = findViewById(R.id.sign);
        mAuth = FirebaseAuth.getInstance();
        btn2_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_email.getText().toString().trim();
                String password = pass_word.getText().toString().trim();
                username = user_name.getText().toString().trim();
                if(email.isEmpty())
                {
                    user_email.setError("Email is empty");
                    user_email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    user_email.setError("Enter the valid email address");
                    user_email.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    pass_word.setError("Enter the password");
                    pass_word.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            myRef.push().setValue(new User(email, username));
                            Toast.makeText(RegistrationPage.this,
                                    "You are successfully Registered",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationPage.this,
                                    LoginPage.class));
                        }
                        else
                        {
                            Toast.makeText(RegistrationPage.this,
                                    "You are not Registered! Try again",Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });

            }
        });


    }
}