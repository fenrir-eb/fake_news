package com.example.jmarkovic16_homework1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmarkovic16_homework1.R;
import com.example.jmarkovic16_homework1.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> implements Filterable {

    private List<Article> articlesDataSet;
    private List<Article> articlesFullDataSet;

    public ArticleAdapter(){
        articlesDataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {
        Article article = articlesDataSet.get(position);
        Picasso.get().load(article.getImgUrl()).into(holder.articleImage);
        holder.articleText.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articlesDataSet.size();
    }

    public void setData(List<Article> articles){
        articlesDataSet.clear();
        articlesDataSet.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ArticleHolder extends RecyclerView.ViewHolder{

        ImageView articleImage;
        TextView articleText;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.iv_grid_item);
            articleText = itemView.findViewById(R.id.tv_grid_item);


        }
    }

}
