package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_VaccineApp);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void launchVaccinpage(View view) {
        Intent intent = new Intent (this, Vaccination_page.class);
        startActivity(intent);
    }

    public void gotohealfaci(View view) {
        Intent intent = new Intent (this, Hospital_lists.class);
        startActivity(intent);
    }

    public void gotowebsite(View view) {
        Intent intent = new Intent (this, covidwebsites.class);
        startActivity(intent);
    }

    public void openthingstoknow(View view) {
        Intent intent = new Intent (this, things_to_know.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                Intent intent = new Intent (this, Login.class);
                startActivity(intent);
                finish();
                //return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}