package com.example.jmarkovic16_homework1.viewmodel;

import com.example.jmarkovic16_homework1.model.Article;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Article>> articleLiveData;

    public MainViewModel(){
        articleLiveData = new MutableLiveData<>();
        List<Article> articles = new ArrayList<>();
        for(int i=0; i<50; i++){
            articles.add(new Article(i,"Article num"+i));
        }
        articleLiveData.setValue(articles);
    }

    public LiveData<List<Article>> getArticles() {
        return articleLiveData;
    }

    public void setArticles(List<Article> articles){
        this.articleLiveData.setValue(new ArrayList<>(articles));
    }
}
