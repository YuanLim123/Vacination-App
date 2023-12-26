package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class registration extends AppCompatActivity {
    TextInputLayout tlname, tlphone, tlic;
    TextInputEditText name, phone, ic;
    MediaPlayer mpPfizer, mpSinovac, mpAz;
    private String vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        ic = findViewById(R.id.edit_ic);

        tlname = findViewById(R.id.TIL_name);
        tlphone = findViewById(R.id.TIL_phone);
        tlic = findViewById(R.id.TIL_ICNumber);

        mpPfizer = new MediaPlayer();
        mpPfizer = MediaPlayer.create(this,R.raw.pfizeraudio);

        mpAz = new MediaPlayer();
        mpAz = MediaPlayer.create(this,R.raw.azaudio);

        mpSinovac = new MediaPlayer();
        mpSinovac = MediaPlayer.create(this,R.raw.sinovacaudio);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void radiobuttoncheck(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radiobutton_pfizer:
                if (checked)
                    displayToast(getString(R.string.pfizer));
                mpPfizer.start();
                vaccine = getString(R.string.pfizer);
                break;
            case R.id.radiobutton_sinovac:
                if (checked)
                    displayToast(getString(R.string.sinovac));
                mpSinovac.start();
                vaccine = getString(R.string.sinovac);
                break;
            case R.id.radiobutton_AZ:
                if (checked)
                    displayToast(getString(R.string.AZ));
                mpAz.start();
                vaccine = getString(R.string.AZ);
                break;
        }
    }


    public void submit(View view) {
        validation check = new validation();

        if (check.checkfieldvalue(name, tlname) && check.checkfieldvalue(phone, tlphone)
                && check.checkfieldvalue(ic, tlic)) {

            DatabaseHelper db = new DatabaseHelper(this);
            String convertIc = ic.getText().toString();
            Boolean taken = db.checkBook(convertIc);
            if (!taken) {
                savedata();
            } else
                Toast.makeText(getApplicationContext(), "You have already registered for vaccination", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), "You have chosen  " + message, Toast.LENGTH_SHORT).show();
    }

    public void displaysuccess() {
        Toast.makeText(getApplicationContext(), "Register successfully  ", Toast.LENGTH_SHORT).show();
    }

    public void savedata() {
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(this);
            String n = name.getText().toString(),
                    p = phone.getText().toString(),
                    i = ic.getText().toString();
            Boolean insertSuccess = dbhelper.insertbooking(n, i, p, vaccine);

            if (insertSuccess) {
                displaysuccess();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong, please try again ", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}