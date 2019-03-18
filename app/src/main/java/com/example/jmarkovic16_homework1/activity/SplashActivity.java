package com.example.jmarkovic16_homework1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import com.example.jmarkovic16_homework1.R;

public class SplashActivity extends AppCompatActivity {

    private static final String URL = "https://picsum.photos/1080/1920/?random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        randomImage();
        checkPreferences();
    }

    private void checkPreferences() {

        SharedPreferences shPref = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        boolean keepLogedIn = shPref.getBoolean("keepLoginPreferencesKey",false);

        if (!keepLogedIn) {
            startActivityWithDelay(LoginActivity.class);
        } else {
            startActivityWithDelay(ArticleActivity.class);
        }
    }

    private void startActivityWithDelay(final Class cls) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, cls);
                    startActivity(intent);
                    finish();
                }
            }, 1500);
        }

    private void randomImage(){
        ImageView imgView = findViewById(R.id.imgView);
        Picasso.get()
                .load(URL)
                .into(imgView);
    }
}
