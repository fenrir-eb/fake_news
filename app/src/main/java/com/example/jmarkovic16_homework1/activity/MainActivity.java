package com.example.jmarkovic16_homework1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jmarkovic16_homework1.R;
import com.example.jmarkovic16_homework1.adapter.ArticleAdapter;
import com.example.jmarkovic16_homework1.model.Article;
import com.example.jmarkovic16_homework1.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME_KEY = "usernameKey";
    private int pressed = 0;

    private EditText etInput;
    private EditText etFilter;
    private MainViewModel viewModel;
    private List<Article> articleList;
    private ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleList = new ArrayList<>();
        initView();
        initViewModels();
    }

    private void initView(){
        etInput = findViewById(R.id.et_main_input);
        etFilter = findViewById(R.id.et_main_filter);
        Button btnAdd = findViewById(R.id.btn_main_add);

        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etInput.getText().toString();
                articleList.add(0,new Article(new Random().nextInt(Integer.MAX_VALUE),title));
                viewModel.setArticles(articleList);
            }
        });

        RecyclerView recycler = findViewById(R.id.rv_main_list);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recycler.setLayoutManager(manager);

        articleAdapter = new ArticleAdapter();
        recycler.setAdapter(articleAdapter);
    }

    private void initViewModels(){
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                articleList = new ArrayList<>(articles);
                articleAdapter.setData(articleList);
            }
        });

    }

    @Override
    public void onBackPressed() {
        pressed++;
        Toast.makeText(this,"Press again to exit!", Toast.LENGTH_SHORT).show();

        if(pressed>1){
            super.onBackPressed();

            SharedPreferences shPref = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            SharedPreferences.Editor editor = shPref.edit();

            editor.putBoolean("loginPreferencesKey",false);
            editor.putBoolean("keepLoginPreferencesKey",false);
            editor.apply();

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}