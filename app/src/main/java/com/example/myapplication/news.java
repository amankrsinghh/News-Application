package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//16b20d9da92a4ae190f3424ceb6103d6
public class news extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<Article> articles = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.newsRecyclerView);
        adapter = new NewsAdapter(this, articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fetchNews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchNews() {
        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything("technology", "");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> fetchedArticles = response.body().articles;
                    Log.d("NEWS_DEBUG", "Articles size: " + fetchedArticles.size());
                    adapter.setData(response.body().articles);
                } else {
                    Toast.makeText(news.this, "Failed to get news", Toast.LENGTH_SHORT).show();
                    Log.e("RESPONSE_CODE", String.valueOf(response.code()));
                    try {
                        Log.e("RESPONSE_ERROR", response.errorBody().string());
                    } catch (Exception e) {
                        Log.e("ERROR_PARSING", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}