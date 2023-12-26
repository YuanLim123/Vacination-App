package com.example.vaccination_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Vaccination_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_VaccineApp);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void toquiz(View view) {
        Intent intent = new Intent (this, quiz.class);
        startActivity(intent);
    }


    public void gotovaccineinfo(View view) {
        Intent intent = new Intent (this, Vaccine_info.class);
        startActivity(intent);

    }
}