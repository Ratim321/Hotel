package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UserProfileEditActivity extends AppCompatActivity {

    TextView userNameDisplay;
    TextInputEditText usernameField, emailField,phoneField;

    String  username, email, phoneNo,id;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        userNameDisplay = findViewById(R.id.userNameDisplay);
        usernameField = findViewById(R.id.username);
        emailField = findViewById(R.id.email);
        phoneField = findViewById(R.id.phone);

    }
}