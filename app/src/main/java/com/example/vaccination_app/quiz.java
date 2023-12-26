package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class quiz extends AppCompatActivity {

    Boolean q1Valid = false, q2Valid = false, q3Valid = false, q4Valid = false;
    int q1Result,q2Result,q3Result,q4Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_quiz);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Boolean checkvalidationQuiz() {
        return q1Valid && q2Valid && q3Valid && q4Valid;
    }

    public void checkQ1(View view) {
        q1Valid = true;
        switch (view.getId()) {
            case R.id.buttonQ1Yes:
                q1Result=1;
                Toast.makeText(quiz.this,"Answer is Correct", Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonQ1No:
                q1Result=0;
                Toast.makeText(quiz.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();

                break;
    }}

    public void checkQ2(View view) {
        q2Valid = true;
        switch (view.getId()) {
            case R.id.buttonQ2Yes:
                q2Result=1;
                Toast.makeText(quiz.this,"Answer is Correct", Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonQ2No:
                q2Result=0;
                Toast.makeText(quiz.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();

                break;
    }}

    public void checkQ3(View view) {
        q3Valid = true;
        switch (view.getId()) {
            case R.id.buttonQ3Yes:
                q3Result=0;
                Toast.makeText(quiz.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonQ3No:
                q3Result=1;
                Toast.makeText(quiz.this,"Answer is Correct", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    public void checkQ4(View view) {
        q4Valid = true;
        switch (view.getId()) {
            case R.id.buttonQ4Yes:
                q4Result=1;
                Toast.makeText(quiz.this,"Answer is Correct", Toast.LENGTH_SHORT).show();

                break;
            case R.id.buttonQ4No:
                q4Result=0;
                Toast.makeText(quiz.this,"Answer is Incorrect", Toast.LENGTH_SHORT).show();

                break;
        }
    }



    public void launchRegister(View view) {
        int total = q1Result + q2Result + q3Result + q4Result;
        if (checkvalidationQuiz()) {

            if (total == 4) {
                Intent intent = new Intent(this, registration.class);
                startActivity(intent);

            } else
                Toast.makeText(quiz.this, "Please make sure all answer is correct", Toast.LENGTH_SHORT).show();
        }
         else
             Toast.makeText(quiz.this, "Please ensure all questions are answered before submitting", Toast.LENGTH_SHORT).show();

}}