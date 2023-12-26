package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Admin_main extends AppCompatActivity {

    EditText inputName, inputIc, inputPhone;
    Spinner inputSpinnerVaccine;
    Button buttonAdd, buttonView;
    DatabaseHelper mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_admin_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mDatabase = new DatabaseHelper(this);
        inputName = findViewById(R.id.etName);
        inputPhone = findViewById(R.id.etPhone);
        inputIc = findViewById(R.id.etIc);
        inputSpinnerVaccine = findViewById(R.id.spinnerVaccine);

        buttonAdd = (Button) findViewById(R.id.btnadd);
        buttonAdd.setOnClickListener((view) -> { addUser(); });

        buttonView = (Button) findViewById(R.id.btview);
        buttonView.setOnClickListener((view) -> { viewData(); });

    }

    private void addUser(){
        String name = inputName.getText().toString();
        String ic = inputIc.getText().toString();
        String phone = inputPhone.getText().toString();
        String vaccine = inputSpinnerVaccine.getSelectedItem().toString();

        //validation
        if(name.isEmpty()) {
            inputName.setError("Name cant be empty");
            inputName.requestFocus();
            return;
        }
        if (ic.isEmpty()){
            inputIc.setError("Ic cant be empty");
            inputIc.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            inputPhone.setError("phone cant be empty");
            inputPhone.requestFocus();
            return;
        }
        //adding the user with the DatabaseHelper instance
        if (mDatabase.insertbooking(name, ic, phone, vaccine))
            Toast.makeText(this,"User Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Could not add User", Toast.LENGTH_SHORT).show();
    }

    public void viewData(){
        Intent intent = new Intent(this, Admin_activity.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}