package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    DatabaseHelper dbHelper;
    TextInputEditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

    }

    public void gotoMainpage(View view) {
        String user = username.getText().toString();
        String pw = password.getText().toString();
        boolean loginSuccessful = dbHelper.checkLogin(user, pw);

        if (user.equals("admin") && pw.equals("admin")) {
            Intent admin = new Intent(getApplicationContext(), Admin_main.class);
            startActivity(admin);
        }
        else if (loginSuccessful) {
            displayToast("Login successful");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            displayToast("Invalid username or password");
            username.setText("");
            password.setText("");
        }
    }

    public void gotoSignup(View view) {

        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
        finish();
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}