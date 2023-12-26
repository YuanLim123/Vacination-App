package com.example.vaccination_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vaccination_app.MainActivity;
import com.example.vaccination_app.R;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView mlottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mlottieAnimationView = findViewById(R.id.animationView);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (SplashScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        };
        Handler h = new Handler();
        h.postDelayed(r,4000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            hideSystemUI();
        }
    }

    private void hideSystemUI(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}