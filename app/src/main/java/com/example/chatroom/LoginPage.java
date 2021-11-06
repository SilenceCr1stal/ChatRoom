package com.example.chatroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {
    static ArrayList<User> listUsers = new ArrayList<>();
    public static String loginUser = "unknown2";
    private EditText user_email, pass_word;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        user_email = findViewById(R.id.email);
        pass_word = findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_sign = findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(v -> {
            String email = user_email.getText().toString().trim();
            String password = pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                user_email.setError("Email is empty");
                user_email.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_email.setError("Enter the valid email");
                user_email.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }
            if (RegistrationPage.username.equals("unknown")) {
                for (int i = 0; i < listUsers.size(); i++) {
                    if (listUsers.get(i).email.equals(email)) {
                        loginUser = listUsers.get(i).username;
                    }
                }
            } else {
                loginUser = RegistrationPage.username;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {

                    startActivity(new Intent(LoginPage.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(LoginPage.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
        RegistrationPage.myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                LoginPage.listUsers.add(user);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_sign.setOnClickListener(v -> startActivity(new Intent(LoginPage.this,
                RegistrationPage.class )));
    }

}