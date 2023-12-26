package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class Hospital_lists extends AppCompatActivity implements HospitalAdapter.OnNoteListener {

    private ArrayList<Hospital> hospitaLists;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_lists);
        recyclerView = findViewById(R.id.recyclerview);
        hospitaLists = new ArrayList<>();


        setHospitalinfo();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_hospital);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        HospitalAdapter Adapter = new HospitalAdapter(hospitaLists, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Adapter);


        SearchView searchview = (SearchView) findViewById(R.id.HospitalSearchView);

        searchview.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Adapter.getFilter().filter(s);
                return false;
            }
        });


    }


    private void setHospitalinfo() {

        hospitaLists.add(new Hospital("Hospital Sultanah Aminah", "State: Johor", R.drawable.hospital_aminah));
        hospitaLists.add(new Hospital("Hospital Melaka", "State: Melaka", R.drawable.hospital_melaka));
        hospitaLists.add(new Hospital("Hospital Ampang", "State: Selangor", R.drawable.hospital_ampang));
        hospitaLists.add(new Hospital("Hospital Pulau Pinang", "State: Penang", R.drawable.hospital_penang));
        hospitaLists.add(new Hospital("Hospital Baling", "State: Kedah", R.drawable.hospital_baling));
        hospitaLists.add(new Hospital("Hospital Sultanah Bahiyah", "State: Kedah", R.drawable.hospital_baling));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteClick(int position) {

        String location = hospitaLists.get(position).getHospitalname();
        Uri locuri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, locuri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}

