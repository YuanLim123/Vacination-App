package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    DatabaseHelper dbhelper;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    TextInputEditText username, password, cpassword;
    TextInputLayout tlusername, tlpassword, tlcpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbhelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.comfirmpassword);

        tlusername = findViewById(R.id.textInputLayoutUsername);
        tlpassword = findViewById(R.id.textInputLayoutPassword);
        tlcpassword = findViewById(R.id.textInputLayoutComfirmPassword);
    }

    public void SignUp(View view) {
        String user = username.getText().toString();
        String pw = password.getText().toString();
        String cpw = cpassword.getText().toString();

        validation valid = new validation();
        if (valid.checkfieldvalue(username, tlusername) && valid.checkfieldvalue(password, tlpassword) && valid.checkfieldvalue(cpassword, tlcpassword)) {
            if (pw.equals(cpw)) {
                Boolean taken = dbhelper.checkUsername(user);
                if (validatePassword()) {
                    if (!taken) {
                        Boolean insertSuccess = dbhelper.insert(user, pw);
                        if (insertSuccess) {
                            displayToast("Registered Successfully");
                            username.setText("");
                            password.setText("");
                            cpassword.setText("");
                        }
                    } else
                        displayToast("Username is already existed");
                }
            } else displayToast("Confirm password mismatched");
        }
    }

    private boolean validatePassword() {
        String pw1 = password.getText().toString();
        if (!PASSWORD_PATTERN.matcher(pw1).matches()) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


    public void gotologin(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}