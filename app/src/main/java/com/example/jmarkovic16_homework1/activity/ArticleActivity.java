package com.example.jmarkovic16_homework1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmarkovic16_homework1.MyLeadingMarginSpan2;
import com.example.jmarkovic16_homework1.R;

public class ArticleActivity extends AppCompatActivity {

    public static final String USERNAME_KEY = "usernameKey";

    private SharedPreferences.Editor editor;
    SharedPreferences shPref;

    private ImageView imgStar;
    private Button btnFavorite;
    private boolean boolFavorite;
    private int pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        init();
    }

    private void init(){
        shPref = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        editor = shPref.edit();

        String username = shPref.getString("usernamePreferencesKey", null);
        String welcome = "Welcome "+username+"!\n"+"Here's an article of the day, like it?";
        ((TextView)findViewById(R.id.txt_welcome)).setText(welcome);


        imgStar = findViewById(R.id.img_star);
        btnFavorite = findViewById(R.id.btn_favorite);
        boolFavorite = shPref.getBoolean("favoritePreferencesKey",false);

        favoriteInit();
        favoriteAction();
        preferencesCleaner();
        formatStarMargin();

    }

    private void favoriteInit(){
        imgStar.setImageResource(!boolFavorite?R.drawable.unstar:R.drawable.star);
        btnFavorite.setText(!boolFavorite?R.string.btn_favorite:R.string.btn_unfavorite);
    }

    private void favoriteAction(){
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolFavorite = !boolFavorite;

                editor.putBoolean("favoritePreferencesKey",boolFavorite);
                editor.apply();

                favoriteInit();
            }
        });
    }

    private void preferencesCleaner(){
        imgStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.apply();

                startLoginActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {
        pressed++;
        Toast.makeText(this,"Press again to exit!", Toast.LENGTH_SHORT).show();

        if(pressed>1){
            super.onBackPressed();
            editor.putBoolean("loginPreferencesKey",false);
            editor.putBoolean("keepLoginPreferencesKey",false);
            editor.apply();

            startLoginActivity();
        }
    }

    private void startLoginActivity(){
        Intent intent = new Intent(ArticleActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void formatStarMargin(){
        String text = getString(R.string.txt_random);
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new MyLeadingMarginSpan2(3, 110), 950, ss.length(), 0);
        ((TextView)findViewById(R.id.txt_random)).setText(ss);

    }
}