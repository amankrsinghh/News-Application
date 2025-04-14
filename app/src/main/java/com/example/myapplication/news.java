package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.newsRecyclerView);
        adapter = new NewsAdapter(this, articles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fetchGeneralNews();

        progressBar = findViewById(R.id.progressbar);

        Button generalButton = findViewById(R.id.General);
        generalButton.setOnClickListener(v -> fetchGeneralNews());

        Button technologyButton = findViewById(R.id.technology);
        technologyButton.setOnClickListener(v -> fetchTechnologyNews());

        Button sportsButton = findViewById(R.id.sports);
        sportsButton.setOnClickListener(v -> fetchSportsNews());

        Button healthButton = findViewById(R.id.health);
        healthButton.setOnClickListener(v -> fetchHealthNews());

        Button businessButton = findViewById(R.id.business);
        businessButton.setOnClickListener(v -> fetchBusinessNews());

        Button entertainmentButton = findViewById(R.id.entertainment);
        entertainmentButton.setOnClickListener(v -> fetchEntertainmentNews());

        Button scienceButton = findViewById(R.id.science);
        scienceButton.setOnClickListener(v -> fetchScienceNews());

        
        
        

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchTechnologyNews() {
        progressBar.setVisibility(View.VISIBLE);
        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything("technology", "16b20d9da92a4ae190f3424ceb6103d6");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.GONE);
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
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
private void fetchHealthNews() {
        progressBar.setVisibility(View.VISIBLE);
    NewsApiService service = ApiClient.getService();
    Call<NewsResponse> call = service.getEverything( "health", "16b20d9da92a4ae190f3424ceb6103d6");

    call.enqueue(new Callback<NewsResponse>() {
        @Override
        public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
            progressBar.setVisibility(View.GONE);
            if (response.isSuccessful() && response.body() != null) {
                List<Article> fetchedArticles = response.body().articles;
                adapter.setData(fetchedArticles);
            } else {
                Toast.makeText(news.this, "Failed to load health news", Toast.LENGTH_SHORT).show();
            }
            recyclerView.scrollToPosition(0);
        }

        @Override
        public void onFailure(Call<NewsResponse> call, Throwable t) {
            Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}
    private void fetchEntertainmentNews() {
        progressBar.setVisibility(View.VISIBLE);
        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything( "entertainment", "16b20d9da92a4ae190f3424ceb6103d6");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> fetchedArticles = response.body().articles;
                    adapter.setData(fetchedArticles);
                } else {
                    Toast.makeText(news.this, "Failed to load health news", Toast.LENGTH_SHORT).show();
                }
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//
    private void fetchBusinessNews() {
        progressBar.setVisibility(View.VISIBLE);
        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything( "business", "16b20d9da92a4ae190f3424ceb6103d6");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> fetchedArticles = response.body().articles;
                    adapter.setData(fetchedArticles);
                } else {
                    Toast.makeText(news.this, "Failed to load health news", Toast.LENGTH_SHORT).show();
                }
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchGeneralNews() {

        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything( "general", "16b20d9da92a4ae190f3424ceb6103d6");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Article> fetchedArticles = response.body().articles;
                    adapter.setData(fetchedArticles);
                } else {
                    Toast.makeText(news.this, "Failed to load health news", Toast.LENGTH_SHORT).show();
                }
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchScienceNews() {
        progressBar.setVisibility(View.VISIBLE);
        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything( "science", "16b20d9da92a4ae190f3424ceb6103d6");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> fetchedArticles = response.body().articles;
                    adapter.setData(fetchedArticles);
                } else {
                    Toast.makeText(news.this, "Failed to load health news", Toast.LENGTH_SHORT).show();
                }
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchSportsNews() {
        progressBar.setVisibility(View.VISIBLE);
        NewsApiService service = ApiClient.getService();
        Call<NewsResponse> call = service.getEverything( "sports", "16b20d9da92a4ae190f3424ceb6103d6");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> fetchedArticles = response.body().articles;
                    adapter.setData(fetchedArticles);
                } else {
                    Toast.makeText(news.this, "Failed to load health news", Toast.LENGTH_SHORT).show();
                }
                recyclerView.scrollToPosition(0);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(news.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}