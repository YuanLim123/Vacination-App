package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Vaccine_info extends AppCompatActivity {
    private TextView pfizer,sinovac,az;
    private ImageButton img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_vaccineinfo);
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

    public void pfizer(View view) {
        pfizer=findViewById(R.id.info_vaccine_pfizer);
        img1=findViewById(R.id.image_expand1);

        if(pfizer.getVisibility()==View.GONE){ //if info not visible
            //set info visible
            pfizer.setVisibility(View.VISIBLE);
            //change the image arrow-downwards
            img1.setImageResource(R.drawable.ic_expand_less_foreground);
        }else{ //if info visible
            //set info gone
           pfizer.setVisibility(View.GONE);
            //change the image arrow-upwards
            img1.setImageResource(R.drawable.ic_expand_foreground);
        }
    }

    public void sinovac(View view) {
        sinovac=findViewById(R.id.info_vaccine_sinovac);
        img2=findViewById(R.id.image_expand2);

        if(sinovac.getVisibility()==View.GONE){ //if info not visible
            //set info visible
            sinovac.setVisibility(View.VISIBLE);
            //change the image arrow-downwards
            img2.setImageResource(R.drawable.ic_expand_less_foreground);
        }else{ //if info visible
            //set info gone
            sinovac.setVisibility(View.GONE);
            //change the image arrow-upwards
            img2.setImageResource(R.drawable.ic_expand_foreground);
        }
    }

    public void az(View view) {
        az=findViewById(R.id.info_vaccine_az);
        img3=findViewById(R.id.image_expand3);

        if(az.getVisibility()==View.GONE){ //if info not visible
            //set info visible
            az.setVisibility(View.VISIBLE);
            //change the image arrow-downwards
            img3.setImageResource(R.drawable.ic_expand_less_foreground);
        }else{ //if info visible
            //set info gone
            az.setVisibility(View.GONE);
            //change the image arrow-upwards
            img3.setImageResource(R.drawable.ic_expand_foreground);
        }
    }
}