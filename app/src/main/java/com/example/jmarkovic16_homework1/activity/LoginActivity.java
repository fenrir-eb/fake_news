package com.example.jmarkovic16_homework1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jmarkovic16_homework1.R;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences shPref;

    private String username;
    private String password;
    private boolean keepLogedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        String packageName = getPackageName();
        shPref = getSharedPreferences(packageName,MODE_PRIVATE);

        init();
    }

    private void init(){
        ((EditText)findViewById(R.id.etxt_user)).setText(shPref.getString("usernamePreferencesKey", null));

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        password = ((EditText)findViewById(R.id.etxt_pass)).getText().toString();
        username = ((EditText)findViewById(R.id.etxt_user)).getText().toString();
        keepLogedIn = ((CheckBox)findViewById(R.id.ckb_login)).isChecked();
//        if(username.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
//            return;
//        }
        saveUsername();
        startArticleActivity();
    }

    private void saveUsername(){
        SharedPreferences.Editor editor = shPref.edit();
        editor.putBoolean("keepLoginPreferencesKey",keepLogedIn);
        editor.putString("usernamePreferencesKey",username);
        editor.putString("passwordPreferencesKey",password);
        editor.apply();
    }

    private void startArticleActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.USERNAME_KEY, username);
        startActivity(intent);
        finish();
    }
}
